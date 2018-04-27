package com.lyn.codeLearing.proxyMode.dynamicProxy.case1;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 动态代理类
 * 代理任意对象
 * 代理类的属性是object类型，实际使用时通过该类的构造方法传递进来一个对象
 * 此外，该类还实现了invoke方法，该方法中的method.invoke其实就是调用被代理对象的将要
 * 执行的方法，方法参数是sub，表示该方法属于sub，通过动态代理类，执行真实对象
 * 的方法
 */
public class DynamicSubject implements InvocationHandler{
    //代理的真实对象
    private Object sub;

    //给要代理的真实对象赋值
    public DynamicSubject(Object obj){
        this.sub=obj;
    }

    /**
     * 动态代理方法
     * @param proxy
     * @param method
     * @param args
     * proxy:　  指代我们所代理的那个真实对象
       method:　指代的是我们所要调用真实对象的某个方法的Method对象
       args:　　 指代的是调用真实对象某个方法时接受的参数
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {


        System.out.println("before calling: " +method);

        method.invoke(sub,args);

        System.out.println("after calling: "+method);

        return null;
    }
}
