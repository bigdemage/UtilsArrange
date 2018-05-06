package com.lyn.codeLearing.Recursion;


/**
 * 递归实现阶乘
 */
public class TestCase1 {

    public static int compute(int number) {
        if (number == 1) {
            return 1;
        } else {
            return number * compute(number - 1);
        }
    }

    public static void main(String[] args) {
        System.out.println(compute(5));
    }
}
