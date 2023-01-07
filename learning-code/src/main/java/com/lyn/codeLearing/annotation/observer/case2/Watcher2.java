package com.lyn.codeLearing.annotation.observer.case2;

import java.util.Observable;
import java.util.Observer;



public class Watcher2 implements Observer{
    @Override
    public void update(Observable o, Object arg) {
        System.out.println("Watcher2:"+arg);
    }
}
