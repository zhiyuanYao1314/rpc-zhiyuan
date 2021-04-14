package com.zhiyuan.register;

import com.zhiyuan.framework.URL;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface Register {

    void regist(String interfaceName, URL url);

    List<URL> get(String interfaceName);

}
