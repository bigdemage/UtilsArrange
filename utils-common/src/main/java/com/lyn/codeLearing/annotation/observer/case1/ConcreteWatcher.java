package com.lyn.codeLearing.annotation.observer.case1;

public class ConcreteWatcher implements Watcher {

    @Override
    public void update(String str) {
        System.out.println(str);
    }
}
