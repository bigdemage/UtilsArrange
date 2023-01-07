package com.lyn.codeLearing.IO.characterStream.output;


import java.io.*;

/**
 * 字符输出流和输入流
 */
public class StreamTest {

    public static void main(String[] args) throws Exception {
        //输出流
//        FileOutputStream fos=new FileOutputStream("file.txt");
//
//        OutputStreamWriter osw =new OutputStreamWriter(fos);
//
//        BufferedWriter bw =new BufferedWriter(osw);
//
//        bw.write("www.mdzz.com \n");
//
//        bw.write("www.nmsl.com");
//
//        bw.close();

        //输入流

        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("file.txt")));

        String str = br.readLine();

        while (str != null) {
            System.out.println(str);
            str = br.readLine();
        }
        br.close();
    }
}
