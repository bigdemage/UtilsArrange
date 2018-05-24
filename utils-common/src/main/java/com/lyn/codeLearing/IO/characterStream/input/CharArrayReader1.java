package com.lyn.codeLearing.IO.characterStream.input;


import java.io.CharArrayReader;
import java.io.IOException;

/**
 * 字符数组读取流？
 */
public class CharArrayReader1 {

    public static void main(String[] args) throws IOException {
        String tmp="abcdef";

        char[] ch=new char[tmp.length()];

        tmp.getChars(0,tmp.length(),ch,0);

        CharArrayReader input =new CharArrayReader(ch);

        int i;

        while ((i=input.read())!=-1){
            System.out.println((char)(i));
        }
    }

}
