package com.zhiyuan.framework;

import java.io.Serializable;
import java.util.Arrays;

/**
 * consumer发送给provider的类 参数
 */
public class Invocation implements Serializable {

    private String interfaceName;
    private String methodName;
    private Object[] params;
    private Class[] paramType;

    public Invocation(String interfaceName, String methodName, Object[] params, Class[] paramType) {
        this.interfaceName = interfaceName;
        this.methodName = methodName;
        this.params = params;
        this.paramType = paramType;
    }

    public String getInterfaceName() {
        return interfaceName;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Object[] getParams() {
        return params;
    }

    public void setParams(Object[] params) {
        this.params = params;
    }

    public Class[] getParamType() {
        return paramType;
    }

    public void setParamType(Class[] paramType) {
        this.paramType = paramType;
    }

    @Override
    public String toString() {
        return "Invocation{" +
                "interfaceName='" + interfaceName + '\'' +
                ", methodName='" + methodName + '\'' +
                ", params=" + Arrays.toString(params) +
                ", paramType=" + Arrays.toString(paramType) +
                '}';
    }
}
