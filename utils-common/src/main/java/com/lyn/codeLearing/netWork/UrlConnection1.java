package com.lyn.codeLearing.netWork;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

public class UrlConnection1 {

    public static void main(String[] args) throws Exception {

        URL url =new URL("https://www.chtwm.com");


        InputStream is =url.openStream();


        byte[] bb =new byte[2048];

        int length=0;

        OutputStream os =new FileOutputStream("chtwm.txt");

        while(-1!=(length=is.read(bb,0,bb.length))){
            os.write(bb,0,length);
        }

        is.close();
        os.close();

    }
}
