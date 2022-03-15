package com.lyn.codeLearing.modelLearning.singleton;


//静态内部类方式的单例
public class StaticSingleton {
    private static class SingletonHoler{
        private static final StaticSingleton INSTANCE=new StaticSingleton();
    };

    private StaticSingleton(){}
    public static final StaticSingleton getInstance(){
        return SingletonHoler.INSTANCE;
    }

}


