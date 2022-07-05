package com.lyn.codeLearing.annotation.case2;


import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface MyAnnotation {
    String hello() default "lyn";

    String world();
}