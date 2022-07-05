package com.lyn.codeLearing.clone;

/**
 * 浅复制
 */
public class CloneTest1 {



    public static void main(String[] args) throws CloneNotSupportedException {
        Student student =new Student(1,"ww");

        Student student2= (Student) student.clone();


        student2.setName("lisi");

        System.out.println(student);
        System.out.println(student2);

    }
}


class Student implements Cloneable{

    private int age;

    private String name;

    @Override
    public String toString() {
        return "Student{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }

    public Student(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Object object=super.clone();

        return object;
    }
}