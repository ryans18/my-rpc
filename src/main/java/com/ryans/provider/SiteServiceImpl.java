package com.ryans.provider;

import com.ryans.api.SiteService;

/**
 * Author：Ryans
 * Date：Created in 2023/8/16 22:06
 * Introduction：
 */
public class SiteServiceImpl implements SiteService {

    @Override
    public String getName(String name) {
        return "hello, " + name;
    }
}