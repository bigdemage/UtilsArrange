package com.lyn.codeLearing.mode.decorator;

public class Client2 {

    public static void main(String[] args) {
        Component component =new ConcreteDecorater1(
                                    new ConcreteDecorater2(
                                        new ConcreteComponent()));

        component.doSomeThing();
    }
}
