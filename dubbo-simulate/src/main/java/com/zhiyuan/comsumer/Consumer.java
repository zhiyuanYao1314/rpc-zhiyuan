package com.zhiyuan.comsumer;

import com.zhiyuan.framework.ProxyFactory;
import com.zhiyuan.provider.api.HelloService;

public class Consumer {

    public static void main(String[] args) {
        //
        HelloService helloService = ProxyFactory.getProxy(HelloService.class);
        String xxx = helloService.sayHello("xxx");
        System.out.println(xxx);

    }
}
