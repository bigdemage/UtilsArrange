package com.lyn.codeLearing.mode.decorator;

/**
 * 具体构建角色
 */
public class ConcreteComponent implements Component {
    @Override
    public void doSomeThing() {
        System.out.println("总功能");
    }
}
