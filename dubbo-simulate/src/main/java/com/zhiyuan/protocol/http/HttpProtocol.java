package com.zhiyuan.protocol.http;

import com.zhiyuan.framework.Invocation;
import com.zhiyuan.framework.Protocol;
import com.zhiyuan.framework.URL;

public class HttpProtocol implements Protocol {

    @Override
    public void start(URL url) {
        HttpServer httpServer = new HttpServer();
        // 开启tomcat服务
        httpServer.start(url.getHostname(), url.getPort());
    }

    @Override
    public String send(URL url, Invocation invocation) {
        // 通过 HttpRequest发送请求
        HttpClient httpClient = new HttpClient();
        return httpClient.send(url.getHostname(), url.getPort(),invocation);
    }
}
