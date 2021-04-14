package com.zhiyuan.spiTest;


import org.apache.dubbo.common.extension.ExtensionLoader;

public class Test {
    public static void main(String[] args) {
        ExtensionLoader<Car> extensionLoader1 = ExtensionLoader.getExtensionLoader(Car.class);
        Car car= extensionLoader1.getExtension("red");
        car.getColor();

//        ExtensionLoader<Driver> extensionLoader = ExtensionLoader.getExtensionLoader(Driver.class);
//        Driver driver= extensionLoader.getExtension("tracker");
//
//        HashMap map = new HashMap();
//        map.put("carType", "black");
//        URL url = new URL("", "", 0, map);
//        driver.getDrive(url);
    }
}
