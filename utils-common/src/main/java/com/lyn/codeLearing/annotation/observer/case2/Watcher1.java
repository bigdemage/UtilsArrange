package com.lyn.codeLearing.annotation.observer.case2;

import java.util.Observable;
import java.util.Observer;

public class Watcher1 implements Observer {
    @Override
    public void update(Observable o, Object arg) {
        int i = (int) arg;
        if(i<5){
            System.out.println("Watcher1:"+i);
        }
    }
}
