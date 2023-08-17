package com.ryans.consumer;

import com.ryans.api.SiteService;
import com.ryans.common.ProxyFactory;

/**
 * Author：Ryans
 * Date：Created in 2023/8/16 22:05
 * Introduction：
 */
public class Consumer {

    public static void main(String[] args) {
        SiteService siteService = ProxyFactory.getProxy(SiteService.class);
        String name = siteService.getName("ryans");
        System.out.println(name);
    }
}