package com.ryans.common;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author：Ryans
 * Date：Created in 2023/8/16 22:11
 * Introduction：模拟远程注册中心
 */
public class RemoteMapRegister {

    private static Map<String, List<URL>> REGISTER = new HashMap<>();

    public static void regist(String interfaceName, URL url) {
        List<URL> urls = REGISTER.get(interfaceName);
        if (urls == null) {
            urls = new ArrayList<>();
        }
        urls.add(url);
        REGISTER.put(interfaceName, urls);
        // 保存到文件，实现provider和consumer之间共享, 两个进程之间不共享数据
        saveFile();
    }

    public static List<URL> get(String interfaceName) {
        REGISTER = getFile();
        return REGISTER.get(interfaceName);
    }

    private static void saveFile() {
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            fos = new FileOutputStream("classpath:register.txt");
            oos = new ObjectOutputStream(fos);
            oos.writeObject(REGISTER);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (oos != null) oos.close();
                if (fos != null) fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
    private static Map<String, List<URL>> getFile() {
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try {
            fis = new FileInputStream("classpath:register.txt");
            ois = new ObjectInputStream(fis);
            return (Map<String, List<URL>>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ois != null) ois.close();
                if (fis != null) ois.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

}