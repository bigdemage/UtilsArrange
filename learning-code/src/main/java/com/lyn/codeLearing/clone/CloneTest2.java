package com.lyn.codeLearing.clone;


/**
 * clone是浅复制
 * 引用不会复制
 * 间接深复制就是在引用时再clone一次
 */
public class CloneTest2 {

    public static void main(String[] args) throws CloneNotSupportedException {

        Teacher teacher=new Teacher(2,"dd");

        Person person=new Person(21,"pp",teacher);

        Person person2 = (Person) person.clone();

        teacher.setName("app");

        System.out.println(person2);







    }
}

class Teacher implements  Cloneable{

    private int age;
    private String name;

    public Teacher(int age, String name) {
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
        return super.clone();
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}

class Person implements  Cloneable{

    private int age;
    private String name;
    private Teacher teacher;


    public Person(int age, String name, Teacher teacher) {
        this.age = age;
        this.name = name;
        this.teacher = teacher;
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

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {

        Person person= (Person) super.clone();

        person.setTeacher((Teacher) person.getTeacher().clone());

        return person;
    }

    @Override
    public String toString() {
        return "Person{" +
                "age=" + age +
                ", name='" + name + '\'' +
                ", teacher=" + teacher +
                '}';
    }
}
