package com.lyn.codeLearing.annotation.case4;

public class DocumentTest {


    @DocumentAnnotation(hello = "welcome")
    public void method(){

        System.out.println("method gogogo");
    }
}
