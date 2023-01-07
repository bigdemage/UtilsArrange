package com.lyn.codeLearing.modelLearning.decorate;

public abstract class Gunsight implements Gun{

    protected Gun gun;

    public Gunsight(Gun gun){
        this.gun=gun;
    }

    public void multiple4x(){
        System.out.println("启动四倍镜");
    }

    @Override
    public void bang() {
        gun.bang();
    }
}
