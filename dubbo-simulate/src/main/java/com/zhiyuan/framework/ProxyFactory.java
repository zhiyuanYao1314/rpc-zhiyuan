package com.zhiyuan.framework;

import com.zhiyuan.register.Register;
import com.zhiyuan.register.RemoteMapRegister;
import com.zhiyuan.register.ZookeeperRegister;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;

public class ProxyFactory<T> {

    private static Register register = new RemoteMapRegister();

    @SuppressWarnings("unchecked")
    public static <T> T getProxy(final Class interfaceClass) {
        return (T)Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class[]{interfaceClass}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                String mock = System.getProperty("mock");
                if (mock != null && mock.startsWith("return:")) {
                    String result = mock.replace("return:", "");
                    return result;
                }
                // 包装接口，方法，参数类型和参数
                Invocation invocation = new Invocation(interfaceClass.getName(), method.getName(), args, method.getParameterTypes());
                // 通过接口名 获取到远程服务的URL
//                List<URL> urlList = RemoteMapRegister.get(interfaceClass.getName());
                List<URL> urlList = register.get(interfaceClass.getName());
                // 负载均衡，选取url
                URL url = LoadBalance.random(urlList);
                // 通过具体的协议，进行发送；
                Protocol protocol = ProtocolFactory.getProtocol();
                String result = protocol.send(url, invocation);
                return result;
            }
        });
    }

}
