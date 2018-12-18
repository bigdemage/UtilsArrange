package com.lyn.codeLearing.netWork;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;


/**
 * 字节流转成字符流
 */
public class UrlConnection2 {
    public static void main(String[] args) throws Exception{
        URL url =new URL("https://www.chtwm.com");

        BufferedReader br=new BufferedReader(
                new InputStreamReader(url.openStream()));

        String line=null;

        while((line=br.readLine())!=null){

            System.out.println(line);
        }

        br.close();

    }
}
