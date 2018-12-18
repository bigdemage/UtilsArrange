package com.lyn.codeLearing.mode.decorator;

/**
 * 装饰角色
 * 相当于一个父类
 * 过滤流
 */
public class Decorater  implements Component{

    private Component component;


    public Decorater(Component component){
        this.component=component;
    }
    @Override
    public void doSomeThing() {
        component.doSomeThing();
    }
}
