package com.lyn.codeLearing.IO.linshi;

import java.io.FileWriter;
import java.io.IOException;



public class FileWriter1 {


    public static void main(String[] args) throws IOException {

        String str="hello mdzz tantan is dog";

        char[] buffer=new char[str.length()];
        //转换字符数组
        str.getChars(0,str.length(),buffer,0);

        FileWriter f =new FileWriter("file2.txt");

        for(int i=0;i<buffer.length;i++){
            f.write(buffer[i]);
        }

        f.close();

    }
}
