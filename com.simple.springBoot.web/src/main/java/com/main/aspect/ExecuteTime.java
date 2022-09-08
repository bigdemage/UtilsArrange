package com.main.aspect;


import java.lang.annotation.*;



@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ExecuteTime {
    String value() default "";
}
