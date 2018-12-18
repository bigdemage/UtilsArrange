package com.lyn.codeLearing.annotation.observer.case2;

import java.util.Observable;
import java.util.Observer;

public class Client {

    public static void main(String[] args) {

        Watcher1 watcher1=new Watcher1();
        Watcher2 watcher2=new Watcher2();

        Watched watched=new Watched();

        watched.addObserver(watcher1);
        watched.addObserver(watcher2);

        watched.numCount(10);




    }
}
