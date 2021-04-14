package com.zhiyuan.protocol.dubbo;

import com.zhiyuan.framework.Invocation;
import com.zhiyuan.framework.Protocol;
import com.zhiyuan.framework.URL;

public class DubboProtocol implements Protocol {

    @Override
    public void start(URL url) {
        NettyServer nettyServer = new NettyServer();
        // 通过netty开启服务
        nettyServer.start(url.getHostname(), url.getPort());

    }

    @Override
    public String send(URL url, Invocation invocation) {
        NettyClient nettyClient = new NettyClient();
        return nettyClient.send(url.getHostname(),url.getPort(), invocation);
    }
}
