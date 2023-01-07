package com.lyn.codeLearing.Arrays.sortCase;

import com.lyn.codeLearing.Arrays.sortCase.Student;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
public class SortList {
    public static void main(String[] args) {
        List<Student> list = new ArrayList<Student>();
        list.add(new Student(1.55, "0.65", 12));
        list.add(new Student(2.55, "0.55", 15));
        list.add(new Student(3.8, "0", 10));
        list.add(new Student(1.45, "0.13", 16));
        list.add(new Student(0.0, "0.ss", 17));
        list.add(new Student(0, "0.ss", 17));
        System.out.println("---Natural Sorting by id---");
        List<Student> alist = list.stream().sorted(Comparator.comparing(Student::getId)).collect(Collectors.toList());
        alist.forEach(e -> System.out.println("Id:"+ e.getId()+", Name: "+e.getName()+", Age:"+e.getAge()));

        List<Student> oolist =list.stream().filter(str->str.getName().equals("0.65")).collect(Collectors.toList());
        System.out.println(oolist);



        System.out.println("---Natural Sorting by Name---");
        List<Student> slist = list.stream().sorted().collect(Collectors.toList());
        slist.forEach(e -> System.out.println("Id:"+ e.getId()+", Name: "+e.getName()+", Age:"+e.getAge()));

        System.out.println("---Natural Sorting by Name in reverse order---");
        slist = list.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        slist.forEach(e -> System.out.println("Id:"+ e.getId()+", Name: "+e.getName()+", Age:"+e.getAge()));

        System.out.println("---Sorting using Comparator by Age---");
        slist = list.stream().sorted(Comparator.comparing(Student::getAge)).collect(Collectors.toList());
        slist.forEach(e -> System.out.println("Id:"+ e.getId()+", Name: "+e.getName()+", Age:"+e.getAge()));

        System.out.println("---Sorting using Comparator by Age with reverse order---");
        slist = list.stream().sorted(Comparator.comparing(Student::getAge).reversed()).collect(Collectors.toList());
        slist.forEach(e -> System.out.println("Id:"+ e.getId()+", Name: "+e.getName()+", Age:"+e.getAge()));



    }
}
