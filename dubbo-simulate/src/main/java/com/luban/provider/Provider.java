package com.luban.provider;

import com.luban.framework.Protocol;
import com.luban.framework.ProtocolFactory;
import com.luban.framework.URL;
import com.luban.protocol.dubbo.NettyServer;
import com.luban.protocol.http.HttpProtocol;
import com.luban.protocol.http.HttpServer;
import com.luban.provider.api.HelloService;
import com.luban.provider.impl.HelloServiceImpl;
import com.luban.register.RemoteMapRegister;
import com.luban.register.ZookeeperRegister;

public class Provider {

    private static boolean isRun = true;

    public static void main(String[] args) {
        // 1. 注册服务
        // 2. 本地注册
        // 3. 启动tomcat

        // 注册服务
        URL url = new URL("localhost", 8080); //NetUtil
        RemoteMapRegister.regist(HelloService.class.getName(), url);

        //  服务：实现类
        LocalRegister.regist(HelloService.class.getName(), HelloServiceImpl.class);


        Protocol protocol = ProtocolFactory.getProtocol();
        protocol.start(url);


    }
}
