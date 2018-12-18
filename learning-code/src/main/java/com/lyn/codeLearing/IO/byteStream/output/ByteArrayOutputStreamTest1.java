package com.lyn.codeLearing.IO.byteStream.output;



import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;

/**
 * 字节数组输出流
 */
public class ByteArrayOutputStreamTest1 {

    public static void main(String[] args) throws Exception {
        ByteArrayOutputStream f =new ByteArrayOutputStream();

        String str="hello mdzz";

        byte[] bytes=str.getBytes();

        f.write(bytes);

        byte[] result=f.toByteArray();

        for (byte bb:result)
            System.out.println((char)bb);

        OutputStream os =new FileOutputStream("test.txt",true);

        os.write(result);

        os.close();
    }
}
