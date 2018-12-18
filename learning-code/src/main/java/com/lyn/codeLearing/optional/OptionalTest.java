package com.lyn.codeLearing.optional;


import java.util.Optional;

public class OptionalTest {
    public static void main(String[] args) {

        Optional<String> optional =Optional.ofNullable("alibaba");

        System.out.println(optional.orElseGet(()->"wwww"));

        Optional<String> optional2 =optional.map(str->str.toUpperCase());

        System.out.println(optional2.get());


        //flatMap方法的mapping函数必须是Optional
        Optional<String> optional3 =optional.flatMap(str->Optional.of(str.toUpperCase()));

        System.out.println(optional3.get());







    }

}
