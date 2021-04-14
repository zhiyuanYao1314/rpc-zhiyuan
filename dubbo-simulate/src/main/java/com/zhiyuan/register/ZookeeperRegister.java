package com.zhiyuan.register;

import com.alibaba.fastjson.JSONObject;
import com.zhiyuan.framework.URL;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.RetryNTimes;
import org.apache.zookeeper.CreateMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * zookeeper注册中心
 */
public class ZookeeperRegister implements Register{

    // zk客户端
    static CuratorFramework client;

    //  这是我的阿里云服务器, 如果使用阿里云服务，需要开启2181的端口，外部才能访问到
    // 如果本地启动zk， 改为 "localhost:2181",
    static String zkURL = "101.132.243.234:2181";

    static {
        client = CuratorFrameworkFactory
                .newClient(zkURL, new RetryNTimes(3, 1000));
        client.start();

    }

    private static Map<String, List<URL>> REGISTER = new HashMap<>();

    // 注册接口到指定路径
    public  void regist(String interfaceName, URL url) {
        try {
            String result = client.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL).forPath(String.format("/dubbo/service/%s/%s", interfaceName, JSONObject.toJSONString(url)), null);
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public  List<URL> get(String interfaceName) {
        List<URL> urlList = new ArrayList<>();

        try {
            List<String> result = client.getChildren().forPath(String.format("/dubbo/service/%s", interfaceName));
            for (String urlstr : result) {
                urlList.add(JSONObject.parseObject(urlstr, URL.class));
            }

            REGISTER.put(interfaceName, urlList);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return urlList;
    }
}
