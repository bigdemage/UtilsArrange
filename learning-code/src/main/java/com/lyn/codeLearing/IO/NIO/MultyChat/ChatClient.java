package com.lyn.codeLearing.IO.NIO.MultyChat;

import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Scanner;

/**
 * @ClassName ChatClient
 * @Deacription 聊天室客户端
 * @Author wrx
 * @Date 2022/6/24/024 17:25
 * @Version 1.0
 **/
@Slf4j
public class ChatClient {

    private Selector selector;

    private SocketChannel socketChannel;

    /**
     * 用户姓名
     */
    private String userName;

    /**
     * 构造
     */
    public ChatClient(String userName){
        try{
            this.userName=userName;
            this.selector=Selector.open();
            this.socketChannel=SocketChannel.open(new InetSocketAddress("127.0.0.1",ChatServer.PORT));
            this.socketChannel.configureBlocking(false);
            this.socketChannel.register(this.selector, SelectionKey.OP_READ);
            log.info("{}客户端就绪",userName);
        }catch (Exception e){
            log.error("客户端初始化失败");
        }
    }

    /**
     * 发送消息
     * @param msg
     */
    public void send(String msg){
        try{
            msg=userName+"说:"+msg;
            this.socketChannel.write(ByteBuffer.wrap(msg.getBytes()));
        }catch (Exception e){
            log.error("发送消息异常",e);
        }
    }

    /**
     * 接收消息
     */
    public void receiveMsg(){
        try {
            int count =selector.select();
            if(count>0){
                Iterator<SelectionKey> iterator=selector.selectedKeys().iterator();
                while(iterator.hasNext()){
                    SelectionKey selectionKey=iterator.next();
                    //监听读事件
                    if(selectionKey.isReadable()){
                        SocketChannel channel= (SocketChannel) selectionKey.channel();
                        ByteBuffer byteBuffer=ByteBuffer.allocate(1024);
                        //消息读入
                        channel.read(byteBuffer);
                        log.info("接收到消息：{}",new String(byteBuffer.array(), CharsetUtil.UTF_8));
                    }
                    iterator.remove();
                }
            }
        } catch (IOException e) {
            log.error("接收消息异常", e);
        }
    }

    public static void main(String[] args) {
        ChatClient chatClient=new ChatClient("");
        //启一个线程，读取信息
        new Thread(()->{
            while(true){
                chatClient.receiveMsg();
            }
        }).start();

        //弄个输入控制台发送信息
        Scanner scanner=new Scanner(System.in);
        while(scanner.hasNextLine()){
            String msg=scanner.next();
            chatClient.send(msg);
        }

    }

}
