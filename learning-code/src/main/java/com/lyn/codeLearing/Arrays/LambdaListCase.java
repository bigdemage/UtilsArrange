package com.lyn.codeLearing.Arrays;

import org.apache.commons.collections.IterableMap;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class LambdaListCase {

        public static void main(String[] args) {
            List<String> list1 = new ArrayList<String>();
            list1.add("1");
            list1.add("2");
            list1.add("3");
            list1.add("7");
            list1.add("8");

            List<String> list2 = new ArrayList<String>();
//            list2.add("10");
//            list2.add("11");
//            list2.add("7");
//            list2.add("8");

            List<Long> list3=new ArrayList<>();
            list3.add(1l);

            //类型转换
            leixingzhuanhuan(list3,list1);
//特定匹配
            pipei(list1);
//集合操作
            jihe(list1,list2);

        }

    private  static <T> void leixingzhuanhuan(List<T> list3,List<String> list1) {
        List<String> intersection33 = list3.stream().map(item -> String.valueOf(item)).collect(toList());
        System.out.println("11:::::--->"+intersection33.size());
        System.out.println("22:::::--->"+intersection33);
        List<String> intersection = list1.stream().filter(item -> intersection33.contains(item)).collect(toList());
        intersection.parallelStream().forEach(System.out :: println);
        System.out.println(intersection.size()==0);
    }

    private static void pipei(List<String> list1) {
                    List<String> strList =list1.stream().filter(item->(item.equals("7") || item.equals("8"))).collect(toList());

            System.out.println("专门匹配后的的list"+strList+" 长度为:"+strList.size());
    }

    private static void jihe(List<String> list1, List<String> list2) {
        // 交集
        List<String> intersection = list1.stream().filter(item -> list2.contains(item)).collect(toList());
        System.out.println("---交集 intersection---");
        intersection.parallelStream().forEach(System.out::println);
        System.out.println(intersection.size() == 0);

        // 差集 (list1 - list2)
        List<String> reduce1 = list1.stream().filter(item -> !list2.contains(item)).collect(toList());
        System.out.println("---差集 reduce1 (list1 - list2)---");
        reduce1.parallelStream().forEach(System.out::println);

        // 差集 (list2 - list1)
        List<String> reduce2 = list2.stream().filter(item -> !list1.contains(item)).collect(toList());
        System.out.println("---差集 reduce2 (list2 - list1)---");
        reduce2.parallelStream().forEach(System.out::println);

        // 并集
        List<String> listAll = list1.parallelStream().collect(toList());
        List<String> listAll2 = list2.parallelStream().collect(toList());
        listAll.addAll(listAll2);
        System.out.println("---并集 listAll---");
        listAll.parallelStream().forEachOrdered(System.out::println);

        // 去重并集
        List<String> listAllDistinct = listAll.stream().distinct().collect(toList());
        System.out.println("---得到去重并集 listAllDistinct---");
        listAllDistinct.parallelStream().forEachOrdered(System.out::println);

        System.out.println("---原来的List1---");
        list1.parallelStream().forEachOrdered(System.out::println);
        System.out.println("---原来的List2---");
        list2.parallelStream().forEachOrdered(System.out::println);
    }

}
