package com.lyn.codeLearing.reflect;


import com.alibaba.fastjson.JSONObject;

import java.lang.reflect.Array;


/**
 * 多维数组
 */
public class MultiArrayTester {

    public static void main(String[] args) {
        int [] dims=new int[] {5,10,15};
        //生成N维度数组
        Object array= Array.newInstance(Integer.TYPE,dims);
        //获取三维数组中的第一维索引为3的数组，是个二维数组
        Object arrayObj=Array.get(array,3);
        //返回数组对应component类型
        Class clazz=arrayObj.getClass().getComponentType();
        //这是个一维数组
        arrayObj=Array.get(arrayObj,5);
        //设置一维数组下标10的值为37
        Array.setInt(arrayObj,10,37);
        int [][][] arrayCast= (int[][][]) array;
        System.out.println(arrayCast[3][5][10]);
        System.out.println(JSONObject.toJSONString(arrayCast));
    }
}
