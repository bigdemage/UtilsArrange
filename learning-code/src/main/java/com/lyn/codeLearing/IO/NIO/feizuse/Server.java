package com.lyn.codeLearing.IO.NIO.feizuse;


import com.lyn.utils.ByteBufferUtil;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;

/**
 * 服务端
 */
@Slf4j
public class Server {

    public static void main(String[] args) {
        ByteBuffer byteBuffer=ByteBuffer.allocate(16);
        try(ServerSocketChannel server=ServerSocketChannel.open()){
            //绑定端口
            server.bind(new InetSocketAddress(8080));

            List<SocketChannel> channels=new ArrayList<>();
            while(true){
                //非阻塞
                server.configureBlocking(false);
                SocketChannel socketChannel=server.accept();
                if(socketChannel!=null){
                    log.info("connecting.....");
                    channels.add(socketChannel);
                }
                for (SocketChannel channel : channels) {
                    channel.configureBlocking(false);
                    //通道中没有数据，返回0不会阻塞线程
                    if(channel.read(byteBuffer)>0){
                        byteBuffer.flip();
                        ByteBufferUtil.debugRead(byteBuffer);
                        byteBuffer.clear();
                        log.info("after reading");
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
