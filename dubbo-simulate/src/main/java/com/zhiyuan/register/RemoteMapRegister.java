package com.zhiyuan.register;

import com.zhiyuan.framework.URL;

import java.io.*;
import java.util.*;

public class RemoteMapRegister implements Register{

    private static Map<String, List<URL>> REGISTER = new HashMap<>();


    public  void regist(String interfaceName, URL url) {
        List<URL> list = REGISTER.get(interfaceName);
        if (list == null) {
            list = new ArrayList<>();

        }
        list.add(url);

        REGISTER.put(interfaceName, list);

        saveFile();
    }

    public  List<URL> get(String interfaceName) {
        REGISTER = getFile();

        List<URL> list = REGISTER.get(interfaceName);
        return list;
    }


    // 保存到本地文件，模拟，远程注册中心
    private static void saveFile() {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("/Users/yaozhiyuan/Desktop/tmp/tmp.txt");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(REGISTER);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 从本地文件获取，模拟，远程注册中心
    private static Map<String, List<URL>> getFile() {
        try {
            FileInputStream fileInputStream = new FileInputStream("/Users/yaozhiyuan/Desktop/tmp/tmp.txt");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            return (Map<String, List<URL>>) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
