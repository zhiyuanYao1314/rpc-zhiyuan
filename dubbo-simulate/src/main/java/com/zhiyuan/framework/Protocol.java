package com.zhiyuan.framework;


import org.apache.dubbo.common.extension.SPI;

@SPI
public interface Protocol {

    /**
     * privider端，通过该方法对外提供服务
     * @param url， 对外开启服务ip:port
     */
    void start(URL url);

    /**
     * consumer端，通过该方法发送请求
     * @param url 发送的地址
     * @param invocation 发送的内容：包含 接口名，方法名，参数类型和参数
     * @return
     */
    String send(URL url, Invocation invocation);
}
