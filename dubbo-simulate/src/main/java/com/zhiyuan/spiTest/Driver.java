package com.zhiyuan.spiTest;

import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.extension.SPI;

@SPI
public interface Driver {

    void getDrive(URL url);
}
