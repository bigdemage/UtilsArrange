package com.lyn.codeLearing.modelLearning.bridge;

public abstract class Shape {

 protected DrawApi drawApi;

    public Shape() {
    }

    public Shape(DrawApi drawApi) {
        this.drawApi = drawApi;
    }

    public abstract void draw();
}
