package com.lyn.codeLearing.annotation.observer.case1;


import java.util.ArrayList;
import java.util.List;

/**
 * 抽象主题角色实现类
 */
public class ConcreteWatched implements Watched{

    /**
     * 存放观察者
     */
    private List<Watcher> watchers =new ArrayList<Watcher>();

    @Override
    public void addWatcher(Watcher watcher) {
        watchers.add(watcher);
    }

    @Override
    public void removeWatcher(Watcher watcher) {
        watchers.remove(watcher);
    }

    @Override
    public void notifyWatchers(String str) {
        //遍历每个观察者，更新信息
        watchers.forEach(watcher->{
            watcher.update(str);
        });
    }
}
