package com.lyn.codeLearing.proxyMode.dynamicProxy.case1;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class VectorProxy implements InvocationHandler{

    private Object proxyObj;

    public VectorProxy(Object proxyObj){
        this.proxyObj=proxyObj;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("kaishi:::"+method);

        if(args!=null){
            for (Object obj:args) {
                System.out.println(obj);
            }
        }

        Object object=method.invoke(proxyObj,args);

        System.out.println("jieshu:::"+method);

        return object;
    }

    public static Object factory(Object obj) {
        Class<?> clazz = obj.getClass();
        return Proxy.newProxyInstance(clazz.getClassLoader(), clazz.getInterfaces(), new VectorProxy(obj));
    }

    public static void main(String[] args) {
//        List v= (List) factory(new Vector());
//
//        v.add("New");
//
//        v.add("York");
//
//        v.remove(0);
//
//        System.out.println("-----------------------------");

        CharSequence sb = (CharSequence) factory(new StringBuffer());

        sb.charAt(0);
    }

}
