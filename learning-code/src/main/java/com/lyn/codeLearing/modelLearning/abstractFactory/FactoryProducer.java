package com.lyn.codeLearing.modelLearning.abstractFactory;

//工厂生产者
public class FactoryProducer {


    public static AbstractFactory getFactory(String type){
        if(type.equals("color")){
            return new ColorFactory();
        }else if(type.equals("shape")){
            return new ShapeFactory();
        }else{
            return null;
        }

    }
}
