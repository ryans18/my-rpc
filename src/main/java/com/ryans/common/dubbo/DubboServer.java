package com.ryans.common.dubbo;

import com.ryans.common.Invocation;
import com.ryans.common.LocalRegister;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Author : Ryans
 * Date : 2023/8/17
 * Introduction :
 */
public class DubboServer {

    public void start(int port) {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            handlerServer(serverSocket);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handlerServer(ServerSocket serverSocket) {
        while (true) {
            InputStream is = null;
            ObjectInputStream ois = null;
            OutputStream os = null;
            ObjectOutputStream oos = null;
            try {
                System.out.println("dubbo server wait cusumer connect");
                // 等待消费者，阻塞
                Socket socket = serverSocket.accept();
                System.out.println("dubbo server connect success");
                is = socket.getInputStream();
                ois = new ObjectInputStream(is);
                Invocation invocation = (Invocation) ois.readObject();
                String interfaceName = invocation.getInterfaceName();
                Class implClass = LocalRegister.get(interfaceName);
                Method method = implClass.getMethod(invocation.getMethodName(), invocation.getParameterTypes());
                Object result = method.invoke(implClass.newInstance(), invocation.getParameters());
                os = socket.getOutputStream();
                oos = new ObjectOutputStream(os);
                oos.writeObject(result);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (oos != null) oos.close();
                    if (os != null) os.close();
                    if (ois != null) ois.close();
                    if (is != null) is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
