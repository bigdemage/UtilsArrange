package com.lyn.codeLearing.mode.proxyMode.dynamicProxy;

/**
 * @ClassName CarImpl
 * @Deacription TODO
 * @Author wrx
 * @Date 2022/6/6/006 15:01
 * @Version 1.0
 **/
public class CarImpl implements Car{
    @Override
    public void start() {
        System.out.println("踩油门");
    }

    @Override
    public void end() {
        System.out.println("熄火");
    }

    @Override
    public void step(int num) {
        System.out.println("执行第"+num+"步");
    }
}
