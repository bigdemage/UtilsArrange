package com.lyn.codeLearing.IO.NIO;


import com.lyn.utils.ByteBufferUtil;
import lombok.extern.slf4j.Slf4j;

import java.nio.Buffer;
import java.nio.ByteBuffer;

/**
 * 工具类测试
 */
@Slf4j
public class ByteBufferUtilTest {

    public static void main(String[] args) {
        ByteBuffer buffer=ByteBuffer.allocate(10);
        buffer.put((byte)97);
        ByteBufferUtil.debugAll(buffer);

        //再写入4个字节
        buffer.put(new byte[]{98,99,100,101,102,103});
        ByteBufferUtil.debugAll(buffer);

        //获取数据
        buffer.flip();
        log.info("get it {}",buffer.get());
        log.info("get it {}",buffer.get());
        ByteBufferUtil.debugAll(buffer);

        //使用compact-会把没读取的数据向前覆盖，然后往后续
        buffer.compact();
        ByteBufferUtil.debugAll(buffer);

        //再次写入
        buffer.put((byte)104);
        buffer.put((byte)105);
        ByteBufferUtil.debugAll(buffer);

        //再次获取
        buffer.flip();
        log.info("get it {}",buffer.get());
        ByteBufferUtil.debugAll(buffer);

    }
}
