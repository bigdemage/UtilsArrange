package com.lyn.utils;

public class Sample {

    public  int v1=1;
    public  Sample(){
        System.out.println("com.lyn.utils.Sample is loaded by: "+this.getClass().getClassLoader());

        new Dog();
    }
}
