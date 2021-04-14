package com.zhiyuan.spiTest;


public class CarWrapper implements Car{

    private Car car;

    public CarWrapper(Car car){
        this.car = car;
    }

    @Override
    public void getColor() {
        System.out.println("wrapper before");
        car.getColor();
        System.out.println("wrapper after");

    }
}
