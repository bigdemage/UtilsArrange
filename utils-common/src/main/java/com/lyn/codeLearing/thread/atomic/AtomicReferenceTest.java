package com.lyn.codeLearing.thread.atomic;


import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import javax.sound.midi.Soundbank;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 原子引用类型
 */
public class AtomicReferenceTest {

    public static void main(String[] args) {
        AtomicReference<String> atoStr =new AtomicReference<String>("A");
        atoStr.compareAndSet("V","B");
        System.out.println(atoStr.get());
        System.out.println(atoStr.getAndSet("D"));
    }


}
