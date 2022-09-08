package com.lyn.codeLearing.mode.proxyMode.dynamicProxy.cglibType;


import com.lyn.codeLearing.mode.proxyMode.dynamicProxy.Bike;
import com.lyn.codeLearing.mode.proxyMode.dynamicProxy.Car;
import com.lyn.codeLearing.mode.proxyMode.dynamicProxy.CarImpl;
import com.lyn.codeLearing.mode.proxyMode.dynamicProxy.JieAnTe;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @ClassName CglibProxyFactory
 * @Deacription cglib动态代理
 * @Author wrx
 * @Date 2022/6/7/007 11:18
 * @Version 1.0
 **/
public class CglibProxyFactory implements MethodInterceptor {

    private Object target;

    public CglibProxyFactory(Object target){
        this.target=target;
    }

    public Object createProxy(){
        //创建enhancer
        Enhancer enhancer=new Enhancer();
        //set需要代理的类
        enhancer.setSuperclass(target.getClass());
        //设置回调
        enhancer.setCallback(this);
        return enhancer.create();
    }

    @Override
    public Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        String methodName=method.getName();
        System.out.println("现在代理的是"+target.getClass().getName()+"类的"+methodName);
        Object invoke=methodProxy.invoke(target,args);
        System.out.println("代理执行结束\n");
        return invoke;
    }

    public static void main(String[] args) {
        Car car =new CarImpl();

        CglibProxyFactory factory=new CglibProxyFactory(car);

        Car proxy = (Car) factory.createProxy();

        proxy.step(3);

        //----------------------代理抽象类

        Bike bike=new JieAnTe();

        factory=new CglibProxyFactory(bike);

        Bike bikeProxy= (Bike) factory.createProxy();


        bikeProxy.whose("西八");


    }
}
