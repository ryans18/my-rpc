package com.ryans.common;

public interface Protocol {

    // 启动时做什么
    void start(URL url);
    // 发送数据
    Object send(URL url, Invocation invocation);
}
