package com.lyn.codeLearing.IO.byteStream.input;

import java.io.ByteArrayInputStream;


/**
 * 字节数组输入流
 */
public class ByteArrayInputStreamTest1 {

    public static void main(String[] args) {
        String temp="abc";

        byte[] b =temp.getBytes();

        ByteArrayInputStream in =new ByteArrayInputStream(b);

        for(int i=0;i<temp.length();i++){
            int c;
            while((c=in.read())!=-1){
                if(i==0){
                    System.out.println((char)c);
                }else{
                    System.out.println(Character.toUpperCase((char)c));
                }
            }
            System.out.println();
            //把位置调到流的开头
            in.reset();
        }


    }
}
