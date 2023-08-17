package com.ryans.common;

import com.ryans.common.dubbo.DubboProtocol;
import com.ryans.common.http.HttpProtocol;

/**
 * Author：Ryans
 * Date：Created in 2023/8/16 22:23
 * Introduction：协议工厂
 */
public class ProtocolFactory {

    public static Protocol getProtocol() {
        // JVM vm options: -Dprotocol=http
        String protocol = System.getProperty("protocol");
        if (protocol == null) return new HttpProtocol();
        switch (protocol) {
            case "dubbo":
                return new DubboProtocol();
            default:
                return new HttpProtocol();
        }
    }
}