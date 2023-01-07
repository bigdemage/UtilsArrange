package com.lyn.codeLearing.annotation.observer.case2;

import java.util.Observable;

public class Watched extends Observable {

    public void numCount(int number){
        for(;number>=0;number--){
            this.setChanged();

            this.notifyObservers(number);
        }
    }
}
