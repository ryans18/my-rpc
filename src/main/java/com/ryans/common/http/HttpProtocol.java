package com.ryans.common.http;


import com.ryans.common.Invocation;
import com.ryans.common.Protocol;
import com.ryans.common.URL;

/**
 * Author：Ryans
 * Date：Created in 2023/8/16 23:22
 * Introduction：
 */
public class HttpProtocol implements Protocol {

    @Override
    public void start(URL url) {
        HttpServer httpServer = new HttpServer();
        httpServer.start(url.getHost(), url.getPort());
    }

    @Override
    public Object send(URL url, Invocation invocation) {
        return new HttpClient().send(url.getHost(), url.getPort(), invocation);
    }
}