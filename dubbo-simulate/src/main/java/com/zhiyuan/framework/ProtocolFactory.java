package com.zhiyuan.framework;

import com.zhiyuan.protocol.dubbo.DubboProtocol;
import com.zhiyuan.protocol.http.HttpProtocol;

public class ProtocolFactory {

    public static Protocol getProtocol() {

        String name = System.getProperty("protocolName");
        if (name == null || name.equals("")) name = "http";
        switch (name) {
            case "http":
                return new HttpProtocol();
            case "dubbo":
                return new DubboProtocol();
            default:
                break;
        }
        return new HttpProtocol();
    }
}
