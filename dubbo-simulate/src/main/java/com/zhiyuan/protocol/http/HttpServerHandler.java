package com.zhiyuan.protocol.http;

import com.alibaba.fastjson.JSONObject;
import com.zhiyuan.framework.Invocation;
import org.apache.commons.io.IOUtils;
import com.zhiyuan.provider.LocalRegister;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class HttpServerHandler {

    public void handler(HttpServletRequest req, HttpServletResponse resp) {

        try {
            Invocation invocation = JSONObject.parseObject(req.getInputStream(), Invocation.class);
            System.out.println("接收到了请求， "+ invocation);
            // 根据获取到的接口名，调用本地对应的Class
            // 通过反射 创建出来对象和方法， 执行方法；
            var interfaceName = invocation.getInterfaceName();
            var implClass = LocalRegister.get(interfaceName);
            Method method = implClass.getMethod(invocation.getMethodName(), invocation.getParamType());
            var result = (String) method.invoke(implClass.newInstance(), invocation.getParams());

            System.out.println("tomcat:" + result);
            IOUtils.write(result, resp.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }


    }
}
