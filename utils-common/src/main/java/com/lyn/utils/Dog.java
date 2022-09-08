package com.lyn.utils;

public class Dog {

    public Dog(){
        System.out.println("com.lyn.utils.Dog is loaded by:"+this.getClass().getClassLoader());
    }
}
