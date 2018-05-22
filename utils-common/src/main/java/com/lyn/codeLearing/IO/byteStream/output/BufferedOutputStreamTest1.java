package com.lyn.codeLearing.IO.byteStream.output;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;

/**
 * 缓冲输出流
 */
public class BufferedOutputStreamTest1 {

    public static void main(String[] args) throws Exception {
        OutputStream os =new FileOutputStream("d:\\1.txt");

        BufferedOutputStream bos =new BufferedOutputStream(os);

        bos.write("http://www.baidu.com".getBytes());

        //如果不关闭，则不会写进文件，因为是缓冲，一定要关闭流
        bos.close();
        os.close();
    }
}
