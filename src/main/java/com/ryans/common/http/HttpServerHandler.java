package com.ryans.common.http;



import com.alibaba.fastjson.JSONObject;
import com.ryans.common.Invocation;
import com.ryans.common.LocalRegister;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Author：Ryans
 * Date：Created in 2023/8/16 22:59
 * Introduction：
 */
public class HttpServerHandler {

    public void handler(HttpServletRequest req, HttpServletResponse resp) {
        BufferedReader reader;
        try {
//            ObjectInputStream objectInputStream = new ObjectInputStream(req.getInputStream());
//            Invocation invocation = (Invocation)objectInputStream.readObject();
            // 获取request里面的body内容
            StringBuilder data = new StringBuilder();
            String line;
            reader = req.getReader();
            while (null != (line = reader.readLine())) {
                data.append(line);
            }
            // fastjson2这里报错，not support ClassForName : java.lang.String，改用fastjson就好了
            Invocation invocation = JSONObject.parseObject(data.toString(), Invocation.class);
            String interfaceName = invocation.getInterfaceName();
            Class implClass = LocalRegister.get(interfaceName);
            Method method = implClass.getMethod(invocation.getMethodName(), invocation.getParameterTypes());
            // 通过反射执行
            String  result = (String) method.invoke(implClass.newInstance(), invocation.getParameters());
            resp.getWriter().write(result);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }
}