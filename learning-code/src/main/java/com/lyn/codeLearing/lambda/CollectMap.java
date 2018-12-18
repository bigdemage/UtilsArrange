package com.lyn.codeLearing.lambda;

import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * stream  List转Map
 */
public class CollectMap {


    public static void main(String[] args) {
        List<A> list= Arrays.asList(new A[]{new A("a",1),new A("b",2),new A("c",3),new A("a",4)});




        Map<String, List<A>> map =list.stream().peek(System.out::println).collect(Collectors.groupingBy(A::getName));

        System.out.println(map);

        System.out.println("-------------------------------------------");

        //partitioning分割
        Map<Boolean, List<A>> map2 =list.stream().collect(Collectors.partitioningBy(a->a.getName().equals("a")));

        System.out.println(map2);


        System.out.println("---------------------------------------------");

        Stream<Locale> locales=Stream.of(Locale.getAvailableLocales());

        Map<String,String> localesMap =locales.collect(Collectors.toMap(
                l->l.getDisplayLanguage(),l->l.getDisplayLanguage(l),(value,newValue)->value)

        );

        System.out.println(localesMap.size());

        System.out.println("--------------------------------------------");

        Stream<Locale> locales2=Stream.of(Locale.getAvailableLocales());

        Map<String,List<Locale>> lMap =locales2.collect(Collectors.groupingBy(Locale::getCountry));

        System.out.println(lMap.get("CH"));


        List<String> ll =new ArrayList<String>();
        ll.forEach(System.out::println);

        String str =String.join(",", ZoneId.getAvailableZoneIds());
        System.out.println(str);



        double o =Double.parseDouble("+1.0");

        System.out.println(o);

    }



}

class  A{

    private String name;
    private int age;

    public A() {
    }

    public A(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "A{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
