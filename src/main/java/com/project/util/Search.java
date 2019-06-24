package com.project.util;

import com.flickr4java.flickr.Flickr;
import com.flickr4java.flickr.FlickrException;
import com.flickr4java.flickr.REST;
import com.flickr4java.flickr.RequestContext;
import com.flickr4java.flickr.photos.Photo;
import com.flickr4java.flickr.photos.PhotoList;
import com.flickr4java.flickr.photos.PhotosInterface;
import com.flickr4java.flickr.photos.SearchParameters;
import com.flickr4java.flickr.util.IOUtilities;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Demonstration of how to search.
 */
@Service
public class Search {
    static String apiKey;

    static String sharedSecret;

    Flickr f;

    REST rest;

    RequestContext requestContext;

    Properties properties;

    public Search() throws IOException {
        String setupPropertiesPath = System.getenv("SETUP_PROPERTIES_PATH");

        InputStream in = null;
        try {

                in = new FileInputStream("C:\\project\\project\\src\\main\\resources\\setup.properties");

            properties = new Properties();
            properties.load(in);
        } finally {
            IOUtilities.close(in);
        }

        f = new Flickr(properties.getProperty("apiKey"), properties.getProperty("secret"), new REST());
//        requestContext = RequestContext.getRequestContext();
//        Auth auth = new Auth();
//        auth.setPermission(Permission.READ);
//        auth.setToken(properties.getProperty("token"));
//        auth.setTokenSecret(properties.getProperty("tokensecret"));
//        requestContext.setAuth(auth);
        Flickr.debugRequest = false;
        Flickr.debugStream = false;
    }

    public ArrayList<String> search(String text) throws FlickrException {
        PhotosInterface photos = f.getPhotosInterface();
        SearchParameters params = new SearchParameters();
        params.setMedia("photos"); // One of "photos", "videos" or "all"
        params.setExtras(Stream.of("media").collect(Collectors.toSet()));
        params.setText(text);
        PhotoList<Photo> results = photos.search(params, 50, 0);

        ArrayList<String> ps = new ArrayList<>();
        results.forEach(p ->
        {
            ps.add(p.getLargeUrl());
        });
        return ps;
    }

    public static void main(String[] args) throws Exception {
        Search t = new Search();
        t.search(args.length == 0 ? "London" : args[0]);
    }
}
