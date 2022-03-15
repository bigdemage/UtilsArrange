package com.lyn.codeLearing.modelLearning.decorate;

public class Main {

    public static void main(String[] args) {
        Gun gun =new GunImpl();
        Gunsight gunsight =new GunsightImp(gun);
        gunsight.multiple4x();
        gunsight.bang();
    }
}
