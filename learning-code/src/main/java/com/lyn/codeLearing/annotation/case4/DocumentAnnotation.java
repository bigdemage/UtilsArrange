package com.lyn.codeLearing.annotation.case4;


import java.lang.annotation.Documented;

//有此注解会生成到doc的接口文件中
@Documented
public @interface DocumentAnnotation
{
    String hello();

}
