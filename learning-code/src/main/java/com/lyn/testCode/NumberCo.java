package com.lyn.testCode;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NumberCo {


    private static final Pattern pattern = Pattern.compile("([\\d])\\1{5}");

    public static void main(String[] args) {
        Matcher matcher = pattern.matcher("111");
        System.out.println(matcher.matches());
    }


}

