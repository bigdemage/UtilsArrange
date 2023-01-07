package com.lyn.codeLearing.modelLearning.abstractFactory;

//抽象工厂
public abstract class AbstractFactory {

    public abstract Shape getShape(String shape);
    public abstract Color getColor(String color);

}
