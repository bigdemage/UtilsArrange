package com.lyn.codeLearing.IO.NIO;

import io.netty.buffer.ByteBuf;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @ClassName NIOClientTest
 * @Deacription 网络客户端
 * @Author wrx
 * @Date 2022/6/24/024 11:36
 * @Version 1.0
 **/
@Slf4j
public class NIOClientTest {

    public static void main(String[] args) throws Exception {
        //开channel，连接ip和端口
        SocketChannel socketChannel=SocketChannel.open();
        InetSocketAddress inetSocketAddress=new InetSocketAddress("127.0.0.1",6666);
        socketChannel.configureBlocking(false);
        //注意，客户端是连接connect，服务端是绑定bind
        boolean isConnect=socketChannel.connect(inetSocketAddress);
        if(!isConnect){
            //连接不成功可以一直循环等待连接
            while(!socketChannel.finishConnect()){
                log.info("尝试连接中...");
                Thread.sleep(5000);
            }
        }
        //连接成功写消息
        String msg = "砸瓦鲁多欧拉欧拉欧拉欧拉欧拉欧拉欧拉欧拉欧拉";
        ByteBuffer byteBuffer=ByteBuffer.wrap(msg.getBytes());
        //数据写入通道
        socketChannel.write(byteBuffer);
        //程序卡在这不关闭
        System.in.read();

    }
}
