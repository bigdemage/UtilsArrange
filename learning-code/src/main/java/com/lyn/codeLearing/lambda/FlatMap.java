package com.lyn.codeLearing.lambda;

import com.alibaba.fastjson.JSONObject;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @ClassName FlatMap
 * @Deacription TODO
 * @Author wrx
 * @Date 2022/4/28/028 9:34
 * @Version 1.0
 **/
public class FlatMap {

    public static void main(String[] args) {
        List<Integer> list= Arrays.asList(1, 2, 3);


        List<Integer> list2= Arrays.asList(4,5,6);

        List list3= Stream.of(list,list2).flatMap(nu->nu.stream()).collect(Collectors.toList());

        System.out.println(JSONObject.toJSONString(list3));

        System.out.println("---------------------------------------");

        List<Integer> aist=Arrays.asList(1,2,3,4,5);

        int he=aist.stream().reduce(0,(a,e)->a+e);

        System.out.println(he);



    }
}
