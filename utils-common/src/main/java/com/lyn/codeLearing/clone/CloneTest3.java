package com.lyn.codeLearing.clone;

import java.io.*;

/**
 * 使用序列化实现深拷贝
 */
public class CloneTest3 {

    public static void main(String[] args) throws Exception {

        Teacher2 teacher = new Teacher2(2, "dd");

        Student2 student= new Student2(22,"sbs",teacher);

        Student2 copyStudent= (Student2) student.deepCopy();

        teacher.setName("wowo");

        System.out.println(copyStudent);

    }
}


class Teacher2 implements Serializable {


    private int age;
    private String name;

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

    public Teacher2(int age, String name) {
        this.age = age;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Teacher2{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}

class Student2 implements Serializable {



    private int age;
    private String name;
    private Teacher2 teacher;

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

    public Teacher2 getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher2 teacher) {
        this.teacher = teacher;
    }

    public Student2(int age, String name, Teacher2 teacher) {
        this.age = age;
        this.name = name;
        this.teacher = teacher;
    }

    @Override
    public String toString() {
        return "Student2{" +
                "age=" + age +
                ", name='" + name + '\'' +
                ", teacher=" + teacher +
                '}';
    }

    public Object deepCopy() throws Exception {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        ObjectOutputStream oos = new ObjectOutputStream(bos);

        //将当前对象写到流里
        oos.writeObject(this);

        //从流里读取

        ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());

        ObjectInputStream ois = new ObjectInputStream(bis);

        return ois.readObject();
    }
}