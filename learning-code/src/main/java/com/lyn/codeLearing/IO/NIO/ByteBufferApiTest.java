package com.lyn.codeLearing.IO.NIO;

import io.netty.util.CharsetUtil;
import org.apache.commons.lang3.CharSetUtils;

import java.nio.ByteBuffer;

/**
 * @ClassName ByteBufferApiTest
 * @Deacription TODO
 * @Author wrx
 * @Date 2022/6/27/027 11:29
 * @Version 1.0
 **/
public class ByteBufferApiTest {

    public static void main(String[] args) {

        String msg="歪比巴卜";

        ByteBuffer byteBuffer=ByteBuffer.allocate(1024);

        byteBuffer.put(msg.getBytes());

        System.out.println(new String(byteBuffer.array(), CharsetUtil.UTF_8));

    }
}
