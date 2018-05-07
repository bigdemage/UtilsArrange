package com.lyn.codeLearing.annotation.observer.case1;

/**
 * 抽象主题角色，被观察的人
 */
public interface Watched {

    //添加观察者
    public void addWatcher(Watcher watcher);

    //删除观察者
    public void removeWatcher(Watcher watcher);

    //通知所有观察者
    public void notifyWatchers(String str);

}
