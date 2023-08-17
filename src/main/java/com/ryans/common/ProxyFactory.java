package com.ryans.common;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;

/**
 * Author：Ryans
 * Date：Created in 2023/8/16 23:04
 * Introduction：
 */
public class ProxyFactory<T> {

    @SuppressWarnings("unchecked")
    public static <T> T  getProxy(final Class interfaceClass) {
        // 动态代理，对传入的类做增强
        return (T)Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class[]{interfaceClass}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                // Invocation对象封装了一次请求的所有参数
                Invocation invocation = new Invocation(interfaceClass.getName(), method.getName(), method.getParameterTypes(), args);
                List<URL> urls = RemoteMapRegister.get(interfaceClass.getName());
                URL url = LoadBalance.random(urls);
                Protocol protocol = ProtocolFactory.getProtocol();
                return protocol.send(url, invocation);
            }
        });
    }

}