package com.lyn.codeLearing.IO.characterStream.input;

import java.io.BufferedReader;
import java.io.FileReader;

public class FileReader1 {


    public static void main(String[] args) throws Exception {


        FileReader fr = new FileReader("D:\\IdeaProjects\\UtilsArrange\\utils-common\\src\\main\\java\\com\\lyn\\codeLearing\\IO\\characterStream\\input\\StreamTest2.java");

        BufferedReader br = new BufferedReader(fr);

        String str;

        while (null != (str = br.readLine())) {
            System.out.println(str);
        }

        br.close();
    }
}
