package com.project.vo;

import java.util.Date;

public class LogVo {
    private Integer logId;

    private String data;

    private String createTime;

    public LogVo(Integer logId, String data, String createTime) {
        this.logId = logId;
        this.data = data;
        this.createTime = createTime;
    }

    public LogVo() {
        super();
    }

    public Integer getLogId() {
        return logId;
    }

    public void setLogId(Integer logId) {
        this.logId = logId;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data == null ? null : data.trim();
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}