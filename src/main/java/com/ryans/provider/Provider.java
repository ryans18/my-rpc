package com.ryans.provider;

import com.ryans.api.SiteService;
import com.ryans.common.*;

/**
 * Author：Ryans
 * Date：Created in 2023/8/16 22:08
 * Introduction：
 */
public class Provider {

    public static void main(String[] args) {
        URL url = new URL("localhost", 8080);

        // 模拟远程注册中心
        RemoteMapRegister.regist(SiteService.class.getName(), url);

        // 指明服务的实现类
        LocalRegister.regist(SiteService.class.getName(), SiteServiceImpl.class);

        Protocol protocol = ProtocolFactory.getProtocol();
        protocol.start(url);
    }

}