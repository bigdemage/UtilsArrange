package com.lyn.codeLearing.IO.byteStream.output;


import java.io.*;

/**
 * 数据流
 */
public class DataStream1 {

    public static void main(String[] args) throws Exception {
        //写出去
        DataOutputStream dos = new DataOutputStream(
                                 //过滤流
                                 new BufferedOutputStream(
                                         //节点流
                                    new FileOutputStream("data.txt")));

        //四个基本数据类型
        byte b =3;
        int i=12;
        char ch ='a';
        float f=3.3f;

        dos.writeByte(b);
        dos.writeInt(i);
        dos.writeChar(ch);
        dos.writeFloat(f);
        //外层关闭内部也会关闭
        dos.close();

        //----------------------------------------------------------------------------------
        //读回来
        DataInputStream in =new DataInputStream(new BufferedInputStream(new FileInputStream("data.txt")));
        //读写顺序保持一致
        System.out.println(in.readByte());
        System.out.println(in.readInt());
        System.out.println(in.readChar());
        System.out.println(in.readFloat());

        in.close();
    }
}
