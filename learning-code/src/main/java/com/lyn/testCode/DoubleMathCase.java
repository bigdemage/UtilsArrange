package com.lyn.testCode;

import java.text.DecimalFormat;

public class DoubleMathCase {

    public static void main(String[] args) {

        int a=1;
        int b=112;
        DecimalFormat df = new DecimalFormat("0.00");//格式化小数
        String num = df.format((float)a/b);//返回的是String类型
        System.out.println((double)a/b);
        System.out.println(Double.parseDouble(num));
    }
}
