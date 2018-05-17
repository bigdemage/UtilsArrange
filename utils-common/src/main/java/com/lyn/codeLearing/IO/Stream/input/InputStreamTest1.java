package com.lyn.codeLearing.IO.Stream.input;

import java.io.FileInputStream;
import java.io.InputStream;

/**
 * 输入流读取文件
 */
public class InputStreamTest1 {


    public static void main(String[] args) throws Exception {
        InputStream inputStream =new FileInputStream("D:\\fileInput.txt");

        byte[] buffer =new byte[200];

        int length=0;
        /**
         * 从第0个位置开始读，最多读200
         */
        while((length=inputStream.read(buffer,0,200))!=-1){
            String str=new String(buffer,0,length);
            System.out.println(str);
        }

        inputStream.close();
    }
}
