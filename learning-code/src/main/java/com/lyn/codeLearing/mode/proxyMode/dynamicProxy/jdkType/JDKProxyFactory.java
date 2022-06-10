package com.lyn.codeLearing.mode.proxyMode.dynamicProxy.jdkType;

import com.lyn.codeLearing.mode.proxyMode.dynamicProxy.Bike;
import com.lyn.codeLearing.mode.proxyMode.dynamicProxy.Car;
import com.lyn.codeLearing.mode.proxyMode.dynamicProxy.CarImpl;
import com.lyn.codeLearing.mode.proxyMode.dynamicProxy.JieAnTe;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @ClassName JDKProxyFactory
 * @Deacription jdk动态代理，反射机制生成实现代理接口的匿名类，实现invocationHandler接口，在调用真实方法前用invocationHandler处理
 * @Author wrx
 * @Date 2022/6/6/006 15:01
 * @Version 1.0
 **/
public class JDKProxyFactory implements InvocationHandler {

    //需要代理的目标对象
    private Object target;

    public JDKProxyFactory(Object target){
        super();
        this.target=target;
    }


    /**
     * 创建代理对象
     * interface jdk代理需要一个接口
     * Proxy 动态生成的类 在Proxy.newProxyInstance后生成 类是实际存在保存在硬盘上 放在元空间里？
     * Method 接口的实现方法
     * invocationHandler 调用的处理，在调用真实方法之前，先调用它的业务
     *
     * @return
     */
    public Object createProxy(){
        //拿类加载器
        ClassLoader classLoader=target.getClass().getClassLoader();
        //拿目标对象的实现接口
        Class<?>[] interfaces=target.getClass().getInterfaces();
        //创建实现invocationHandler的对象
        Object newProxyInstance= Proxy.newProxyInstance(classLoader,interfaces,this);
        return newProxyInstance;
    }

    /**
     * 调用逻辑
     * @param proxy
     * @param method
     * @param args
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("方法进来");
        Object result=method.invoke(target,args);
        System.out.println("方法出去");
        return result;
    }

    public static void main(String[] args) {
        //真实对象
        Car car =new CarImpl();
        //创建代理工厂
        JDKProxyFactory factory=new JDKProxyFactory(car);
        //代理对象
        Car carProxy= (Car) factory.createProxy();

        carProxy.end();

        //--------------------------------------------代理个抽象类试试，会报错傻卵


        Bike bike=new JieAnTe();

        factory=new JDKProxyFactory(bike);

        Bike bikeProxy= (Bike) factory.createProxy();

        bikeProxy.whose("猛男");

    }
}
