package com.lyn.codeLearing.innerClass.localInner;


/**
 * 局部内部类
 */
public class LocalClass {

    public static void main(String[] args) {

        LocalInnerClass localInnerClass=new LocalInnerClass();

        localInnerClass.doSomeThing();

    }


}

class LocalInnerClass{
    public void doSomeThing(){

        final int  a=4;

        class InnerClass{
            public void test(){
                System.out.println(a);
            }
        }

        new InnerClass().test();

    }

}
