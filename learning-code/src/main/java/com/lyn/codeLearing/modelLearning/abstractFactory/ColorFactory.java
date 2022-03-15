package com.lyn.codeLearing.modelLearning.abstractFactory;

import org.apache.commons.lang3.StringUtils;

public class ColorFactory extends AbstractFactory{
    @Override
    public Shape getShape(String shape) {
        return null;
    }

    @Override
    public Color getColor(String color) {
        if(StringUtils.isBlank(color)){
            return null;
        }else if(color.equals("red")){
            return new Red();
        }else if(color.equals("blue")){
            return new Blue();
        }else{
            return null;
        }
    }
}
