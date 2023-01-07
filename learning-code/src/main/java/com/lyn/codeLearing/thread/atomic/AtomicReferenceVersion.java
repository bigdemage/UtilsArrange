package com.lyn.codeLearing.thread.atomic;


import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.util.concurrent.atomic.AtomicMarkableReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * 带版本戳的原子引用类型
 * AtomicStampedReference带版本戳引用类型，版本戳为int类型
 * AtomicMarkableReference带版本戳引用类型，版本戳为boolean类型
 */
public class AtomicReferenceVersion {


    private static AtomicStampedReference<String> stampedReference =new AtomicStampedReference<String>("A",1);

    private static AtomicMarkableReference<Integer> markableReference=new AtomicMarkableReference<Integer>(1,false);

    private static int type=1;

    /**
     * type：
     * 0 AtomicStampedReference测试用例
     * 1 AtomicMarkableReference测试用例
     * @param args
     */
    public static void main(String[] args) {
        switch (type){
            case 0:
                atomicStampedReferenceTest();
                break;
            case 1:
                atomicMarkableReference();
                break;
        }
    }


    public static void atomicStampedReferenceTest(){
        System.out.println("获取引用对象:"+stampedReference.getReference());
        System.out.println("获取版本戳:"+stampedReference.getStamp());
        //重新设置版本戳和引用对象
        stampedReference.set("B",2);
        System.out.println("重设后的引用对象:"+stampedReference.getReference());
        System.out.println("重设后的版本戳:"+stampedReference.getStamp());
        System.out.println("引用类型为自己想要时，更新版本戳:"+stampedReference.attemptStamp("B",3));
        System.out.println("attemptStamp后的版本号:"+stampedReference.getStamp());
        //compareAndset版本号和引用正确，设置新版本号和新引用
        stampedReference.compareAndSet("B","C",3,4);
        System.out.println("重设后的引用对象:"+stampedReference.getReference());
        System.out.println("重设后的版本戳:"+stampedReference.getStamp());
        //get方法返回引用，传参的数组得到版本戳
        //返回版本戳为数组类型是因为，数组类型可以传递引用，用基本类型不行
        int[] stamps ={1};
        System.out.println("使用get(数组)取值:"+stampedReference.get(stamps));
        System.out.println("使用get(数组)取值后的版本戳:"+stamps[0]);
    }

    public static void atomicMarkableReference(){
        System.out.println("获取引用对象:"+markableReference.getReference());
        System.out.println("获取版本戳:"+markableReference.isMarked());
        //重新设置版本戳和引用对象
        markableReference.set(2,true);
        System.out.println("重设后的引用对象:"+markableReference.getReference());
        System.out.println("重设后的版本戳:"+markableReference.isMarked());
        System.out.println("引用类型为自己想要时，更新版本戳:"+markableReference.attemptMark(2,false));
        System.out.println("attemptStamp后的版本号:"+markableReference.isMarked());
        //compareAndset版本号和引用正确，设置新版本号和新引用
        markableReference.compareAndSet(2,3,false,true);
        System.out.println("重设后的引用对象:"+markableReference.getReference());
        System.out.println("重设后的版本戳:"+markableReference.isMarked());
//        get方法返回引用，传参的数组得到版本戳
        //返回版本戳为数组类型是因为，数组类型可以传递引用，用基本类型不行
        boolean[] stamps ={false};
        System.out.println("使用get(数组)取值:"+markableReference.get(stamps));
        System.out.println("使用get(数组)取值后的版本戳:"+stamps[0]);


    }



}
