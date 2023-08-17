package com.ryans.common.http;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Author：Ryans
 * Date：Created in 2023/8/16 22:58
 * Introduction：
 */
public class DispatcherServlet extends HttpServlet {

    // 接收用户发来的request和返回的响应
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        new HttpServerHandler().handler(req, resp);
    }
}