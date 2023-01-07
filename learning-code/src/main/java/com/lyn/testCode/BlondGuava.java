package com.lyn.testCode;

import com.google.common.base.Charsets;
import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

/**
 * @ClassName BlondGuava
 * @Deacription guava实现布隆过滤器
 * @Author wrx
 * @Date 2022/3/25/025 9:17
 * @Version 1.0
 **/
public class BlondGuava {

    private static final int insertions=1000000;

    private static final double fpp=0.02;

    public static void main(String[] args) {


        /**
         * 构造函数
         * param1：数据类型
         * param2：长度
         * param3：误报率，误报率越大空间和时间越小，反之+
         * 100w数据生成的位图只占用了0.87M，5个hash函数
         */
        BloomFilter<String> bf = BloomFilter.create(Funnels.stringFunnel(Charsets.UTF_8), insertions, fpp);

        bf.put("a");

        System.out.println(bf.mightContain("a"));
        System.out.println(bf.mightContain("aaaaAAA"));


    }
}
