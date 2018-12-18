package com.lyn.codeLearing.serializable;

import java.io.*;

public class Test1 {


    public static void main(String[] args) throws Exception {
        Person p1 = new Person(20, "mdzz", 4.55);
        Person p2 = new Person(21, "nmsl", 4.67);
        Person p3 = new Person(10, "wang", 17.67);

        //序列化到文件
        FileOutputStream fos = new FileOutputStream("serializable.txt");

        ObjectOutputStream oos = new ObjectOutputStream(fos);

        oos.writeObject(p1);
        oos.writeObject(p2);
        oos.writeObject(p3);

        oos.close();

        //反序列化
        FileInputStream fis = new FileInputStream("serializable.txt");
        ObjectInputStream ois = new ObjectInputStream(fis);

        System.out.println(ois.readObject());
        System.out.println(ois.readObject());
        System.out.println(ois.readObject());

        ois.close();
    }

}


class Person implements Serializable {

    int age;
    transient String name;
    double height;

    public Person(int age, String name, double height) {
        this.age = age;
        this.name = name;
        this.height = height;
    }

    public Person() {
    }

    @Override
    public String toString() {
        return "Person{" +
                "age=" + age +
                ", name='" + name + '\'' +
                ", height=" + height +
                '}';
    }



}
