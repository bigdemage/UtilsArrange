package com.lyn.codeLearing.annotation.case2;


@MyAnnotation(hello = "beijing" ,world = "shanghai")
public class MyTest {


    @MyAnnotation(hello = "tianjin" ,world = "shangdi")
    @SuppressWarnings("unchecked")
    @Deprecated
    public void output(){
        System.out.println("out put ");
    }
}
