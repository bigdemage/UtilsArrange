package com.lyn.codeLearing.mode.proxyMode.dynamicProxy.case2;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class CommonInvocation implements InvocationHandler {
   private Object obj;

   public CommonInvocation(Object obj){
       this.obj=obj;
   }

   public CommonInvocation(){

   }



    public void setObj(Object obj) {
        this.obj = obj;
    }

    @Override

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

       return method.invoke(obj,args);
    }
}
