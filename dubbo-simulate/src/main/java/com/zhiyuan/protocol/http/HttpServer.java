package com.zhiyuan.protocol.http;

import org.apache.catalina.*;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.core.StandardEngine;
import org.apache.catalina.core.StandardHost;
import org.apache.catalina.startup.Tomcat;

/**
 * http 服务 由 启动tomcat服务启动和执行，
 */
public class HttpServer {
    // tomcat,jetty

    public void start(String hostname, Integer port) {
        Tomcat tomcat = new Tomcat();

        /**
         * tomcat中，每个service 由多个connector和container组合， connector负责接受请求，交给conatiner容器进行处理
         * 容器的包含关系是：engine -> host -> context
         */
        Server server = tomcat.getServer();
        Service service = server.findService("Tomcat");

        Connector connector = new Connector();
        connector.setPort(port);

        Engine engine = new StandardEngine();
        engine.setDefaultHost(hostname);

        Host host = new StandardHost();
        host.setName(hostname);

        String contextPath = "";
        Context context = new StandardContext();
        context.setPath(contextPath);
        context.addLifecycleListener(new Tomcat.FixContextListener());

        host.addChild(context);
        engine.addChild(host);

        service.setContainer(engine);
        service.addConnector(connector);
        // DispatcherServlet 自己定义一个Servlet实现
        tomcat.addServlet(contextPath, "dispatcher", new DispatcherServlet());
        // 一个context对应一个应用，
        // 所有该应用都交给dispatcher这个servlet；
        context.addServletMappingDecoded("/*", "dispatcher");

        System.out.println("服务正式开启");
        try {
            tomcat.start();
            tomcat.getServer().await();
        } catch (LifecycleException e) {
            e.printStackTrace();
        }
    }
}
