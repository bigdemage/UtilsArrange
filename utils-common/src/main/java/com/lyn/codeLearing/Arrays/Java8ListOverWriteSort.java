package com.lyn.codeLearing.Arrays;


import org.apache.commons.lang3.RandomUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * java8 List元素排序重写
 */
public class Java8ListOverWriteSort {

    public static void main(String[] args) {
        List<User> userList =new ArrayList<User>();
        for(int i=0;i<10;i++){
            userList.add(new User("p"+i,RandomUtils.nextInt(1,30)));
        }
        userList.forEach(user->{
            if(user.getName().equals("p3")) return;
            System.out.println(user);
        });

//        System.out.println("排序前: "+userList);
//
//        List<User> slist = userList.stream().sorted(Comparator.comparing(User::getAge)).collect(Collectors.toList());
//
//        System.out.println("升序后:"+slist);
//
//        userList.sort(Comparator.comparing(User::getAge).reversed());
//
//        System.out.println("降序后:"+userList);
//
//        System.out.println("劳资是分割线--------------------------------------------------");



    }



    static class User {
        private String name;
        private int age;

        public User(String name, int age) {
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
            return "User{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }
}

