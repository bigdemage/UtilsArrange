package com.lyn.codeLearing.annotation.observer.case1;

public class TestMain {

    public static void main(String[] args) {
        Watched girl =new ConcreteWatched();

        Watcher boy1=new ConcreteWatcher();
        Watcher boy2=new ConcreteWatcher();
        Watcher boy3=new ConcreteWatcher();

        girl.addWatcher(boy1);
        girl.addWatcher(boy2);
        girl.addWatcher(boy3);

        girl.notifyWatchers("mdzz");

        girl.removeWatcher(boy2);

        girl.notifyWatchers("good");

    }
}
