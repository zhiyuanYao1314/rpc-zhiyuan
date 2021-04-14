package com.zhiyuan.provider;

import com.zhiyuan.framework.Protocol;
import com.zhiyuan.framework.ProtocolFactory;
import com.zhiyuan.framework.URL;
import com.zhiyuan.provider.api.HelloService;
import com.zhiyuan.provider.impl.HelloServiceImpl;
import com.zhiyuan.register.Register;
import com.zhiyuan.register.RemoteMapRegister;
import com.zhiyuan.register.ZookeeperRegister;

public class Provider {

    private static boolean isRun = true;

    private static Register register = new RemoteMapRegister();

    public static void main(String[] args) {
        // 1. 注册服务到注册中心map{接口，url}，这里，我们模拟，将注册信息保存在硬盘中；
        // 2. 本地注册，map{接口，实现类}
        // 3. 启动tomcat

        // 注册服务
        URL url = new URL("localhost", 8080); //NetUtil

//        RemoteMapRegister.regist(HelloService.class.getName(), url);
        register.regist(HelloService.class.getName(), url);


        //  服务：实现类
        LocalRegister.regist(HelloService.class.getName(), HelloServiceImpl.class);

        // 启动 对外服务，
        // 因为可以有多个不同的协议，
        // 因此这里采用ProtocolFactory，
        Protocol protocol = ProtocolFactory.getProtocol();
        protocol.start(url);

    }
}
