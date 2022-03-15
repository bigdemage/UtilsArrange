package com.lyn.codeLearing.modelLearning.abstractFactory;

public class Main {

    public static void main(String[] args) {

        ColorFactory colorFactory= (ColorFactory) FactoryProducer.getFactory("color");

        Color color= colorFactory.getColor("red");

        color.fill();

    }
}
