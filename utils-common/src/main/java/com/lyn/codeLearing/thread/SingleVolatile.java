package com.lyn.codeLearing.thread;

import java.time.LocalDate;

/**
 * 双重检查的单利
 */
public class SingleVolatile {

    private static SingleVolatile singleVolatile =null;

    public static  SingleVolatile getInstance(){
        if(singleVolatile==null){  //0:非空判断
            synchronized (SingleVolatile.class){
                if(singleVolatile==null){
                    singleVolatile=new SingleVolatile(); //1：new 对象  2：引用指针指向对象地址
                }
            }
        }
        return singleVolatile; //3：返回对象
    }

    public static void main(String[] args) {

        LocalDate date1 = LocalDate.of(2018, 12, 4);
        LocalDate date2 = LocalDate.of(1989, 10, 20);
        System.out.println("year:"+date2.until(date1).getYears());
        System.out.println("month:"+date2.until(date1).getMonths());
        System.out.println("days:"+date2.until(date1).getDays());
        int age = date2.until(date1).getYears();

        System.out.println("You're " + age + " years old.");
    }

    }

