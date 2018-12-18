package com.lyn.codeLearing.reflect;


/**
 * 使用反射，打破类的封装，使用私有方法
 * 破坏了类的封装性，面向对象不太推荐
 */
public class PrivateTest {

    private String sayHello(String name){
        return name;
    }
}
