package com.lyn.codeLearing.mode.decorator;

public class Client {

    public static void main(String[] args) {


        //节点流
        Component component =new ConcreteComponent();
        //过滤流
        Component component2 =new ConcreteDecorater1(component);


        Component component3 =new ConcreteDecorater2(component);


        component2.doSomeThing();


    }
}
