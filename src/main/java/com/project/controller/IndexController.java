package com.project.controller;

import com.flickr4java.flickr.FlickrException;
import com.project.common.ServerResponse;
import com.project.dao.UserMapper;
import com.project.pojo.User;
import com.project.util.Search;
import org.opensaml.saml2.core.impl.NameIDImpl;
 import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;

@RestController
public class IndexController {
    @Autowired
    private UserMapper userMapper;
    private static Search searchs;

    static {
        try {
            searchs = new Search();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/")
    public void index(HttpServletRequest request, HttpServletResponse response, Principal principal) {
        org.springframework.security.providers.ExpiringUsernameAuthenticationToken k = (org.springframework.security.providers.ExpiringUsernameAuthenticationToken)principal;
        NameIDImpl n = (NameIDImpl)k.getPrincipal();
        System.out.println( n.getValue());
        User user = new User();
        user.setName(n.getValue());
        userMapper.insert(user);
        try {
            response.sendRedirect("http://localhost:8080/#/islogin");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @RequestMapping("/search")
    public ServerResponse search(HttpServletResponse response, String name) {
        try {
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Access-Control-Allow-Headers", "X-Requested-With");
            response.setHeader("Access-Control-Allow-Methods","PUT,POST,GET,DELETE,OPTIONS");
            response.setHeader("Content-Type", "application/json;charset=utf-8");

            return ServerResponse.createBySuccess(searchs.search(name));
        } catch (FlickrException e) {
            e.printStackTrace();
        }
        return null;
    }
}