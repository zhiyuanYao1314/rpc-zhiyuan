package com.zhiyuan.framework;

import java.util.List;
import java.util.Random;

/**
 * 负载均衡模块，
 * @author yaozhiyuan
 */
public class LoadBalance {

    public static URL random(List<URL> list) {
        Random random =new Random();
        int n = random.nextInt(list.size());
        return list.get(n);
    }



}
