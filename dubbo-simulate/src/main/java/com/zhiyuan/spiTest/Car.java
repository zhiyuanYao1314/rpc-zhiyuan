package com.zhiyuan.spiTest;


import org.apache.dubbo.common.extension.SPI;

@SPI
public interface Car {

//    @Adaptive(value = "carType")
//    void getColor(URL url);
    void getColor();
}
