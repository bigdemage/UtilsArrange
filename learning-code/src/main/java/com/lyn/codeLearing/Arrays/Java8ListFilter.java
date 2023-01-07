package com.lyn.codeLearing.Arrays;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * java8中用Stream和filter替代for循环
 */
public class Java8ListFilter {

    public static void main(String[] args) {
        List<People> list =new ArrayList<People>();
        list.add(new People("lll",20));
        list.add(new People("www",21));
        list.add(new People("qqq",22));
        list.add(new People("eee",23));

        List<People> list2 = list.stream().filter(p -> "lll".equals(p.getName())).collect(Collectors.toList());

        System.out.println(list2);
        System.out.println("-------------------------------------------");

        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        Stream<Integer> stream = numbers.stream();
        stream.filter((x) -> {
            return x % 2 == 0;
        }).map((x) -> {
            return x * x;
        }).forEach(System.out::println);

        System.out.println("----------------------------------------------");


        List<Integer> numbers2 = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        numbers2.stream().limit(2).forEach(System.out::println);
        System.out.println("***********************************");
        numbers2.stream().skip(3).limit(2).forEach(System.out::println);



    }

}



class People {



    private String name;
    private int age;



    public People(String name, int age) {
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
        return "People{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
