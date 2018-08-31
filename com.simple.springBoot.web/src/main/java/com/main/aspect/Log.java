package com.main.aspect;


import java.lang.annotation.*;

//target:注解的作用目标
//ElementType.TYPE:接口、类、枚举、注解
//ElementType.FIELD:字段、枚举的常量
//ElementType.METHOD:方法
//ElementType.CONSTRUCTOR:构造函数
//ElementType.LOCAL_VARIABLE:局部变量
//ElementType.ANNOTATION_TYPE:注解
//ElementType.PACKAGE:包
@Target({ElementType.METHOD,ElementType.TYPE})
// RetentionPolicy.SOURCE:注解只保留在源文件，编译成class文件时候，注解被遗弃；
//RetentionPolicy.CLASS:注解被保留在class文件，但jvm加载class时候会被遗弃，默认值；
//RetentionPolicy.CLASS:注解不仅被保存到class文件中，jvm加载之后仍然存在；
@Retention(RetentionPolicy.RUNTIME)//运行时
//Documented:注解表明这个注解应该被 javadoc工具记录.
// 默认情况下,javadoc是不包括注解的.
// 但如果声明注解时指定了 @Documented,则它会被 javadoc 之类的工具处理, 所以注解类型信息也会被包括在生成的文档中
@Documented
public @interface Log {
    String description()  ;
}
