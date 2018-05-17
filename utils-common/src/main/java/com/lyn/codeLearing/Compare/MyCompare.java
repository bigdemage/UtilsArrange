package com.lyn.codeLearing.Compare;


import java.util.Comparator;
import java.util.TreeSet;

/**
 * 重写compare
 */
public class MyCompare {

    public static void main(String[] args) {

        TreeSet treeSet = new TreeSet(new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                return ((Person) o1).name.compareTo(((Person) o2).name);
            }
        });
        Person p1 = new Person("add");
        Person p2 = new Person("ab");
        Person p3 = new Person("e");
        treeSet.add(p1);
        treeSet.add(p2);
        treeSet.add(p3);


        System.out.println(treeSet);

        Integer i1=128;
        Integer i2 =128;
        System.out.println(i1==i2);


    }


}


class Person {
    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Person(String name) {
        this.name = name;
    }

    public Person() {
    }

    @Override
    public String toString() {
        return this.name;
    }
}