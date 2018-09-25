package com.main.aspect;


import java.lang.annotation.*;

/**
 * 方法日志注解
 * 方法入参，方法出参，方法执行时间
 */
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MethodLog {
    String description() default "";
}
