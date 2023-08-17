package com.ryans.common.dubbo;

import com.ryans.common.Invocation;
import com.ryans.common.Protocol;
import com.ryans.common.URL;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;

/**
 * Author : Ryans
 * Date : 2023/8/17
 * Introduction :
 */
public class DubboProtocol implements Protocol {

    @Override
    public void start(URL url) {
        new DubboServer().start(url.getPort());
    }

    @Override
    public Object send(URL url, Invocation invocation) {
        Socket socket = null;
        OutputStream os = null;
        ObjectOutputStream oos = null;
        InputStream is = null;
        ObjectInputStream ois = null;
        try {
            socket = new Socket(url.getHost(), url.getPort());
            os = socket.getOutputStream();
            oos = new ObjectOutputStream(os);
            oos.writeObject(invocation);
            // 接收结果
            is = socket.getInputStream();
            ois = new ObjectInputStream(is);
            return ois.readObject();
//            isr = new InputStreamReader(is);
//            br = new BufferedReader(isr);
//            StringBuilder builder = new StringBuilder();
//            String line = null;
//            while (null != (line = br.readLine())) {
//                builder.append(line);
//            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
