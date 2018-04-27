package com.lyn.codeLearing.annotation.case1;


/**
 * 自定义注解
 * @interface是自动继承了Annotation接口
 * 定义Annotation形态时，不能再继承其他Annotation接口
 * Annotation本身是接口，不是注解
 */
public @interface AnnotationTest {
        //设置默认值在使用中就可以不用赋值
        String value() default "hello";
        EnumTest value2() ;

}

enum EnumTest
{
    HELLO,WORLD,WELCOME
}
