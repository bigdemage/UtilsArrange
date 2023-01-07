package com.lyn.codeLearing.Arrays.genericity;


import org.apache.log4j.Logger;

import java.lang.Comparable;

public class ArraysGenericity {

    private final static Logger LOGGER = Logger.getLogger(ArraysGenericity.class);


    public static <T extends Comparable> int countIndex(T[] arrs, T index) {
        int count = 0;
        for (T arr : arrs) {
            if (arr.compareTo(index) > 0) {
                ++count;
            }
        }
        return count;
    }


    public static void main(String[] args) {
        GenericMehtod<Integer> g =new GenericMehtod<>();
        g.setT(2);
        g.print(1);
        g.out("1");
        g.out2();
        g.out3(new User("1"),new User("2"));
        int ii =0;

    }

    static class User{
        private String name;

        public User(String name) {
            this.name = name;
        }
    }



    static class GenericMehtod<T> {
        private T t;

        public T getT() {
            return t;
        }

        public void setT(T t) {
            this.t = t;
        }

        /**
         * <p>Title: 这是一个普通方法</p>
         *
         * @author 韩超 2018/2/22 15:32
         */
        void print(T t) {
            //这里的t是传递过来的参数，只能是泛型类实例化时指明的泛型类型
            LOGGER.info(t.getClass().toString());
        }

        /**
         * <p>Title: 泛型方法1：泛型类与泛型方法中的泛型并无关系</p>
         *
         * @author 韩超 2018/2/22 15:29
         */
        <T> void out(T t) {
            //这里的t是传递过来的参数，与类定义的泛型类型无关系
            LOGGER.info(t.getClass().toString());
        }

        /**
         * <p>Title: 泛型方法2：泛型类与泛型方法中的泛型并无关系</p>
         *
         * @author 韩超 2018/2/22 15:29
         */
        <E> void out2() {
            //这里的t是类中的泛型的实例化对象
            //这个泛型方法没有意义：因为虽然在返回方法之前定义了泛型类型<E>，但是在参数列表中并没有使用这个泛型类型
            LOGGER.info(t.getClass().toString());
        }

        /**
         * <p>Title: 泛型方法3：可变参数</p>
         *
         * @author 韩超 2018/2/22 15:44
         */
        <T> void out3(T... args) {
            for (T t : args) {
                LOGGER.info(t.getClass().toString() + ":" + t);
            }
        }

    }

}
