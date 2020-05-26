package com.luban.comsumer;

import com.luban.framework.Invocation;
import com.luban.framework.ProxyFactory;
import com.luban.protocol.http.HttpClient;
import com.luban.provider.api.HelloService;

public class Consumer {

    public static void main(String[] args) {

        HelloService helloService = ProxyFactory.getProxy(HelloService.class);
        String xxx = helloService.sayHello("xxx");
        System.out.println(xxx);



    }
}
