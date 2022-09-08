package com.lyn.codeLearing.IO.NIO;


import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.functors.FalsePredicate;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @ClassName NIOServerTest
 * @Deacription NIO服务端，网络通信如何使用channel，网络的channel要基于selector选择器去做
 * @Author wrx
 * @Date 2022/6/23/023 10:43
 * @Version 1.0
 **/
@Slf4j
public class NIOServerTest {

    public static void main(String[] args) throws Exception {
        //打开一个serversocketChannel
        ServerSocketChannel serverSocketChannel=ServerSocketChannel.open();
        InetSocketAddress inetSocketAddress=new InetSocketAddress("127.0.0.1",6666);
        //绑定地址
        serverSocketChannel.bind(inetSocketAddress);
        //设置非阻塞，channel要注册到selector中，一定要是非阻塞
        serverSocketChannel.configureBlocking(false);
        //创建selector选择器
        Selector selector=Selector.open();
        //serverSocketChannel注册到选择器中,选择一个监听的事件,有accept，connetion，read，write,serversocketchannel只支持accept
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        //循环等待客户端连接
        while(true){
            //等待10s，返回0表示没有连接
            if(selector.select(10000)==0){
                log.info("等待了10s没有连接");
                continue;
            }
            //如果连接获取/select(10000)>0，获取事件,selectionKey是一个个的事件，事件类型很多，看拿到的是什么事件，对不同的事件做不同处理
            Set<SelectionKey> selectionKeys= selector.selectedKeys();
            //迭代器迭代
            Iterator<SelectionKey> iterator= selectionKeys.iterator();
            while(iterator.hasNext()){
                SelectionKey selectionKey=iterator.next();
                //收到一个客户端连接请求就绪
                if(selectionKey.isAcceptable()){
                    //服务端与客户端建立连接，拿到socketChannel
                    SocketChannel socketChannel=serverSocketChannel.accept();
                    //设置非阻塞
                    socketChannel.configureBlocking(false);
                    /**
                     *  socketChannel绑定到selector上，并且监听读事件，读的东西放哪？缓冲区啊傻瓜卵，别忘
                     *  连接已经建立了就开始监听读或者写事件，别人发我消息，当然就是监听读了
                     */
                    socketChannel.register(selector,SelectionKey.OP_READ, ByteBuffer.allocate(1024));
                }
                //服务端没有connetion事件，是客户端的事件，客户端connetion
                if(selectionKey.isConnectable()){

                }
                //读事件
                if(selectionKey.isReadable()){
                    //获取通道然后获取通道里的缓冲区，缓冲区里就是要读取的数据
                    SocketChannel socketChannel= (SocketChannel) selectionKey.channel();
                    //selectKye的附件就是要读取的东西
                    ByteBuffer byteBuffer= (ByteBuffer) selectionKey.attachment();
                    //通道读取,上面拿下来的byteBuffer最上面register里的byteBuffer对象，里面没有内容，需要用channel把内容读进去
                    socketChannel.read(byteBuffer);
                    log.info("来自客户端{}的消息:{}",socketChannel.getRemoteAddress(),new String(byteBuffer.array(), CharsetUtil.UTF_8));
                }
                //写事件
                if(selectionKey.isWritable()){

                }
                //事件集合中删除已处理的事件
                iterator.remove();
            }
        }

    }
}
