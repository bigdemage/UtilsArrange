package com.lyn.codeLearing.IO.NIO;

import com.lyn.utils.ByteBufferUtil;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

public class TranslateByteBuffer {

    public static void main(String[] args) {

        String str1="kimura";
        String str2="";

        ByteBuffer byteBuffer=ByteBuffer.wrap(str1.getBytes());
        ByteBufferUtil.debugAll(byteBuffer);


        //使用standardCharsets会自动切换成读模式
        str2= StandardCharsets.UTF_8.decode(byteBuffer).toString();
        System.out.println(str2);
        ByteBufferUtil.debugAll(byteBuffer);
    }
}
