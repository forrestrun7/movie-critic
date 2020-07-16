package com.crawler.nw.util;

public class Response {
    public String status;
    public Object content;

    public Response(String status, Object content){
        this.status = status;
        this.content = content;
    }
}
