package com.lyn.codeLearing.modelLearning.bridge;

public class ShapeImpl extends Shape{


    public ShapeImpl(DrawApi drawApi){
        super(drawApi);
    }

    @Override
    public void draw() {
        drawApi.draw();
    }
}
