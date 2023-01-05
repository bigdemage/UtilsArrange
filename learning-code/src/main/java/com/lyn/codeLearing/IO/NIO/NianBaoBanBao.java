package com.lyn.codeLearing.IO.NIO;


import com.lyn.utils.ByteBufferUtil;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

/**
 * 粘包、半包
 */
public class NianBaoBanBao {
    public static void main(String[] args) {
        ByteBuffer buffer=ByteBuffer.allocate(32);
        buffer.put("Hello,world\nI'm Nyima\nHo".getBytes());
        spilit(buffer);
        buffer.put("w are you?\n".getBytes());
        spilit(buffer);

    }

    private static void spilit(ByteBuffer buffer) {
        buffer.flip();
        for(int i=0;i< buffer.limit();i++){
            if(buffer.get(i)=='\n'){
                int length=i+1-buffer.position();
                ByteBuffer target=ByteBuffer.allocate(length);
                for(int j=0;j<length;j++){
                    target.put(buffer.get());
                }
                ByteBufferUtil.debugAll(target);
            }
        }
        buffer.compact();
    }
}
