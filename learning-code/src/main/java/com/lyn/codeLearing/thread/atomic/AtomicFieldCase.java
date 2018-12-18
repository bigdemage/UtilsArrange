package com.lyn.codeLearing.thread.atomic;


import org.apache.log4j.Logger;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicLongFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/**
 * AtomicIntegerField
 * 原子类型字段更新器
 * 3种类型：
 *      AtomicIntegerFieldUpdater：基于反射的工具类，可以原子性的更新指定对象的指定int类型字段。
 *      AtomicLongFieldUpdater：基于反射的工具类，可以原子性的更新指定对象的指定long类型字段。
 *      AtomicReferenceFieldUpdater：基于反射的工具类，可以原子性的更新指定对象的指定应用类型字段。
 * 注意:
 *      字段必须是volatile类型的，用于保证可见性。
 *      字段和字段更新器的访问类型(public/protected/private)必须一致。
 *      字段只能是实例变量，不能是类变量(static)。
 *      字段不能是final的变量，这样的字段不可修改。
 *      如果要处理Integer和Long类型，则需要使用AtomicReferenceFieldUpdater
 */
public class AtomicFieldCase {

    private final static Logger LOGGER=Logger.getLogger(AtomicFieldCase.class);


    public static void main(String[] args) {

        MyVolatileType myVolatileType =new MyVolatileType(15,"ali",10);

//        myVolatileType.fieldUpdaterCommonMethodDemo();

        AtomicReferenceFieldUpdater selfUpdater =AtomicReferenceFieldUpdater.newUpdater(MyVolatileType.class,String.class,"str");

        LOGGER.info("初始输出:"+selfUpdater.get(myVolatileType));
        selfUpdater.set(myVolatileType,"pdd");
        LOGGER.info("set后输出:"+selfUpdater.get(myVolatileType));



    }

    static class MyVolatileType {

        private volatile int index;

        private static AtomicIntegerFieldUpdater integerFieldUpdater = AtomicIntegerFieldUpdater.newUpdater(MyVolatileType.class, "index");

        public volatile String str;

        private static AtomicReferenceFieldUpdater referenceFieldUpdater = AtomicReferenceFieldUpdater.newUpdater(MyVolatileType.class, String.class, "str");

        private volatile long account;

        private static AtomicLongFieldUpdater longFieldUpdater = AtomicLongFieldUpdater.newUpdater(MyVolatileType.class, "account");

        @Override
        public String toString() {
            return "MyVolatileType{" +
                    "index=" + index +
                    ", str='" + str + '\'' +
                    ", account=" + account +
                    '}';
        }

        public MyVolatileType(int index, String str, long account) {
            this.index = index;
            this.str = str;
            this.account = account;
        }

        public MyVolatileType(){

        }

        public void fieldUpdaterCommonMethodDemo() {
            LOGGER.info("=======字段更新器-通用方法演示-以AtomicReferenceFieldUpdater为例");
            LOGGER.info("get(obj):获取值----初始值：" + referenceFieldUpdater.get(this));
            referenceFieldUpdater.set(this, "New Day!");
            LOGGER.info("set(obj,newValue):设置值---" + referenceFieldUpdater.get(this));
            referenceFieldUpdater.lazySet(this, "Lazy Day!");
            LOGGER.info("lazySet(obj,newValue):设置值(无可见性)---" + referenceFieldUpdater.get(this));
            LOGGER.info("getAndSet(obj,newValue):赋值，并返回旧值：" + referenceFieldUpdater.getAndSet(this, "Good Day!"));
            LOGGER.info("compareAndSet(obj,expect,newValue):如果是期望的值,则赋值,并返回赋值结果："
                    + referenceFieldUpdater.compareAndSet(this, "Good Day!", "Good good Day!")
                    + ",---" + referenceFieldUpdater.get(this) + "\n");
            LOGGER.info("赋值完成，现在结果为:"+referenceFieldUpdater.get(this));
        }

    }
}


