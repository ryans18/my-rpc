package com.ryans.common;

import java.lang.reflect.UndeclaredThrowableException;
import java.util.List;
import java.util.Random;

/**
 * Author : Ryans
 * Date : 2023/8/17
 * Introduction :
 */
public class LoadBalance {
    public static URL random(List<URL> urls) {
        if (urls == null) throw new RuntimeException("provider is not open");
        Random random = new Random();
        return urls.get(random.nextInt(urls.size()));
    }
}
