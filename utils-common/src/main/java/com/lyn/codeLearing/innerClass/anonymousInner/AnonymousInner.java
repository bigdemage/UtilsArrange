package com.lyn.codeLearing.innerClass.anonymousInner;

import java.util.Date;

/**
 * 匿名内部类
 */
public class AnonymousInner {
    @SuppressWarnings("deprecation") //压制检查
    public String get(Date date) {
        return date.toLocaleString();
    }

    public static void swap(int a,int b){
        a=a+b;

        b=a-b;

        a=a-b;

        System.out.println(a+","+b);
    }


    public static void main(String[] args) {
        /**
         * 匿名内部类可以重写类中的方法
         * 例如就把date类中的toLocaleString方法重写了
         */
        String date = new AnonymousInner().get(new Date() {
            @Override
            @SuppressWarnings("deprecation")
            public String toLocaleString() {
                return "hlo";
            }
        });

        System.out.println(date);


        swap(10,20);
    }
}
