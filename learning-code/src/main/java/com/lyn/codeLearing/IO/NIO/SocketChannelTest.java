package com.lyn.codeLearing.IO.NIO;

import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * @ClassName SocketChannelTest
 * @Deacription 通过TCP读写网络中的数据
 * @Author wrx
 * @Date 2022/6/22/022 15:45
 * @Version 1.0
 **/
@Slf4j
public class SocketChannelTest {
    public static void main(String[] args) throws Exception {
        //获取serverSocketChannel
        ServerSocketChannel serverSocketChannel=ServerSocketChannel.open();
        //开启ip/端口
        InetSocketAddress address=new InetSocketAddress("127.0.0.1",6666);
        //绑定ip/端口
        serverSocketChannel.bind(address);
        //创建缓冲区
        ByteBuffer byteBuffer=ByteBuffer.allocate(1024);
        while(true){
            //获取socketChannel
            SocketChannel socketChannel=serverSocketChannel.accept();
            while(socketChannel.read(byteBuffer)!=-1){
                System.out.println("打印结果:"+new String(byteBuffer.array(), CharsetUtil.UTF_8));
                //清空缓冲区
                byteBuffer.clear();
            }
        }
    }
}
