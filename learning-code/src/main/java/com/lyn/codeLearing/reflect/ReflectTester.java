package com.lyn.codeLearing.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectTester {
    //对象的拷贝操作
    public Object copy(Object object) throws Exception {
        //生成类
        Class<?> classType = object.getClass();

        Object copyObj = classType.getConstructor(new Class[]{})
                .newInstance(new Object[]{});

        //无参构造器以上两行代码等价于下面一行
        //但是带参数的构造器下面一行代码是搞不定的
//        Object obj2 = classType.newInstance();
        //获得成员变量
        Field[] fields=classType.getDeclaredFields();
        for (Field field:fields) {
            String name=field.getName();
            //将属性首字母大写
            String firstLetter=name.substring(0,1).toUpperCase();
            String getMethodName="get"+firstLetter+name.substring(1);
            String setMethodName="set"+firstLetter+name.substring(1);
            Method getMethod=classType.getMethod(getMethodName,new Class[]{});
            Method setMethod=classType.getMethod(setMethodName,new Class[]{field.getType()});
            Object value=getMethod.invoke(object,new Object[]{});
            setMethod.invoke(copyObj,new Object[]{value});
        }
        return copyObj;
    }

    public static void main(String[] args) throws Exception {
        Customer customer=new Customer("lyn",20);
        customer.setId(1L);

        ReflectTester r = new ReflectTester();

        Customer customer2= (Customer) r.copy(customer);

        System.out.println(customer2);

    }

}


class Customer {

    private Long id;
    private String name;
    private int age;

    public Customer() {
    }

    public Customer(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
