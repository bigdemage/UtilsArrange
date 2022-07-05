package com.lyn.codeLearing.IO.byteStream.output;

import java.io.FileOutputStream;
import java.io.OutputStream;

public class OutputStreamTest1 {
    public static void main(String[] args) throws Exception {
        OutputStream os=new FileOutputStream("D:\\fileOutput.txt",true);

        String str="   wowowow";

        byte[] buffer =str.getBytes();

        os.write(buffer);

        os.close();


    }
}
