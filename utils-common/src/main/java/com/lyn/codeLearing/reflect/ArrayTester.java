package com.lyn.codeLearing.reflect;

import com.alibaba.fastjson.JSONObject;

import java.lang.reflect.Array;

/*
   一维数组
 */
public class ArrayTester {

    public static void main(String[] args) throws Exception {
        Class<?> clazz=Class.forName("java.lang.String");
        //生成字符串数组
        Object array= Array.newInstance(clazz,10);
        //设置array第五个元素
        Array.set(array,5,"hello");
        //提取array第五个元素
        Array.get(array,5);

        System.out.println(JSONObject.toJSONString(array));
    }
}
