package com.lyn.codeLearing.modelLearning.abstractFactory;

import org.apache.commons.lang3.StringUtils;

public class ShapeFactory extends AbstractFactory {
    @Override
    public Shape getShape(String shape) {
        if(StringUtils.isBlank(shape)){
            return null;
        }else if(shape.equals("circle")){
            return new Circle();
        }else if(shape.equals("square")){
            return new Square();
        }else{
            return null;
        }
    }

    @Override
    public Color getColor(String color) {
        return null;
    }
}
