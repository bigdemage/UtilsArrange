package com.lyn.codeLearing.guardQueue;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @ClassName GuardQueue
 * @Deacription TODO
 * @Author wrx
 * @Date 2021/9/7/007 15:06
 * @Version 1.0
 **/
public class GuardQueue {

    private final Queue<Integer> sourceList;

    public GuardQueue() {
        this.sourceList = new LinkedBlockingQueue<>();
    }

    public synchronized Integer get() {
        while (sourceList.isEmpty()) {
            try {
                System.out.println("没人呐，进入等待");
                wait();    // <--- 如果队列为null，等待
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return sourceList.peek();
    }

    public synchronized void put(Integer e) {
        sourceList.add(e);
        notifyAll();  //<--- 通知，继续执行
    }

}
