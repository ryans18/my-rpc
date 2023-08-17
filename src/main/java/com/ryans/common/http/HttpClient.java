package com.ryans.common.http;

import com.alibaba.fastjson.JSON;
import com.ryans.common.Invocation;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.HttpStatus;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
/**
 * Author：Ryans
 * Date：Created in 2023/8/16 23:39
 * Introduction：
 */
public class HttpClient {

    public String send(String host, int port, Invocation invocation) {
        try {
            URL url = new URL("http", host, port, "/");

            CloseableHttpClient httpClient = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost(url.toURI());
            httpPost.setHeader("Accept", "application/json");
            httpPost.setHeader("Content-type", "application/json; charset=utf-8");
            httpPost.setEntity(new StringEntity(JSON.toJSONString(invocation)));
            CloseableHttpResponse response = httpClient.execute(httpPost);
            if (response.getCode() == HttpStatus.SC_OK) {
                return EntityUtils.toString(response.getEntity());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}