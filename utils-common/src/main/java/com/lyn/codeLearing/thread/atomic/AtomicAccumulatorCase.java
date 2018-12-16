package com.lyn.codeLearing.thread.atomic;


import org.apache.commons.lang3.RandomUtils;

import java.util.concurrent.atomic.LongAdder;

/**
 * 原子类型累加器用例
 */
public class AtomicAccumulatorCase {

    /*
        LongAdder所使用的思想就是热点分离，这一点可以类比一下ConcurrentHashMap的设计思想。
        就是将value值分离成一个数组，当多线程访问时，通过hash算法映射到其中的一个数组进行计数。
        而最终的结果，就是这些数组的求和累加。这样一来，就减小了锁的粒度.
        1.LongAdder和LongAccumulator是AtomicLong的扩展
        2.DoubleAdder和DoubleAccumulator是AtomicDouble的扩展
        3.在低并发环境下性能相似；在高并发环境下---吞吐量增加，但是空间消耗增大
        4.多用于收集统计数据，而非细粒度计算
    */
    public static void main(String[] args) {
        longAdderBasicApi();
    }

    /**
     * LongAdder基础api
     */
    private static void longAdderBasicApi() {
        LongAdder longAdder =new LongAdder();
        System.out.println("构造:"+longAdder);
        //自增
        longAdder.increment();
        System.out.println("自增:"+longAdder);
        //自减
        longAdder.decrement();
        System.out.println("自减:"+longAdder);
        //运算
        long index=0;
        for(int i=1;i<6;i++){
            index= RandomUtils.nextLong(100,300);
            System.out.println("循环第"+i+"次的随机数"+index);
            longAdder.add(index);
            System.out.println("第"+i+"次运算后的结果："+longAdder);
        }
        System.out.println("最终运算后的结果并重置:"+longAdder.sumThenReset());
        //重置
        longAdder.reset();
        System.out.println("重置后的值："+longAdder);
    }
}
