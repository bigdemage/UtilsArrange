package com.lyn.codeLearing.IO.characterStream.input;


import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 字符输入流
 */
public class StreamTest2 {

    public static void main(String[] args) throws Exception {
        InputStreamReader isr=new InputStreamReader(System.in);

        BufferedReader br=new BufferedReader(isr);

        String str;

        while(null!=(str=br.readLine())){
            System.out.println(str);
        }

        br.close();

    }
}
