package com.lyn.codeLearing.IO.NIO;

import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.CharSetUtils;

import java.nio.ByteBuffer;

/**
 * @ClassName ByteBufferApiTest
 * @Deacription TODO
 * @Author wrx
 * @Date 2022/6/27/027 11:29
 * @Version 1.0
 **/
@Slf4j
public class ByteBufferApiTest {

    public static void main(String[] args) {

        String msg="歪比巴卜";

        ByteBuffer byteBuffer=ByteBuffer.allocate(1024);

        byteBuffer.put(msg.getBytes());

        log.info("{},{}",byteBuffer.position(),byteBuffer.limit());

        byteBuffer.flip();


        log.info("{},{}",byteBuffer.position(),byteBuffer.limit());

        log.info("读取{}",byteBuffer.get());

        byteBuffer.mark();

        log.info("读取{}",byteBuffer.get());

        log.info("{},{}",byteBuffer.position(),byteBuffer.limit());

        byteBuffer.reset();

        log.info("{},{}",byteBuffer.position(),byteBuffer.limit());



    }
}
