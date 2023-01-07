package com.lyn.codeLearing.modelLearning.bridge;

public class Main {

    public static void main(String[] args) {

        Shape shape =new ShapeImpl(new BlueDraw());
        shape.draw();


    }
}
