package com.lyn.testCode;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NumberCo {

    public static void main(String[] args) {


        Pattern pattern = Pattern.compile("([\\d])\\1{5}");
        Matcher matcher = pattern.matcher("111");
        System.out.println(matcher.matches()); //true
    }


    }

