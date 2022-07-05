package com.lyn.codeLearing.serializable;

import java.io.*;

public class Test2 {


    public static void main(String[] args) throws Exception {
        Person2 p1 = new Person2(20, "mdzz", 4.55);
        Person2 p2 = new Person2(21, "nmsl", 4.67);
        Person2 p3 = new Person2(10, "wang", 17.67);

        //序列化到文件
        FileOutputStream fos = new FileOutputStream("serializable2.txt");

        ObjectOutputStream oos = new ObjectOutputStream(fos);

        oos.writeObject(p1);
        oos.writeObject(p2);
        oos.writeObject(p3);

        oos.close();

        //反序列化
        FileInputStream fis = new FileInputStream("serializable2.txt");
        ObjectInputStream ois = new ObjectInputStream(fis);

        System.out.println(ois.readObject());
        System.out.println(ois.readObject());
        System.out.println(ois.readObject());

        ois.close();
    }

}


class Person2 implements Serializable {

    int age;
    transient String name;
    double height;

    public Person2(int age, String name, double height) {
        this.age = age;
        this.name = name;
        this.height = height;
    }

    public Person2() {
    }

    @Override
    public String toString() {
        return "Person2{" +
                "age=" + age +
                ", name='" + name + '\'' +
                ", height=" + height +
                '}';
    }


    /**
     *
     * 允许我们更加底层，更加细粒度方式控制序列化和反序列化
     * @param out
     * @throws IOException
     */
    private void writeObject(java.io.ObjectOutputStream out)
            throws IOException {
        out.writeObject(this.age);
        out.writeObject(this.name);
        out.writeObject(this.height);
        System.out.println("write");
    }

    private void readObject(java.io.ObjectInputStream in)
            throws IOException, ClassNotFoundException {
        age = (int) in.readObject();
        name = (String) in.readObject();
        height = (double) in.readObject();
        System.out.println("read");

    }


}
