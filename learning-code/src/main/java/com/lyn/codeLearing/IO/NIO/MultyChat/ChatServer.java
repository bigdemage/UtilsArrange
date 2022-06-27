package com.lyn.codeLearing.IO.NIO.MultyChat;

import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

/**
 * @ClassName ChatServer
 * @Deacription 多人聊天室服务端
 *              服务端是负责把客户端a的消息传递到客户端b上
 * @Author wrx
 * @Date 2022/6/24/024 14:42
 * @Version 1.0
 **/
@Slf4j
public class ChatServer {

    /**
     * 选择器
     */
    private Selector selector;

    /**
     * 管道
     */
    private ServerSocketChannel serverSocketChannel;

    /**
     * 端口
     */
    public static final int PORT=6667;

    /**
     * 构造服务端
     * 基础的那些玩意
     */
    public ChatServer(){
        try {
            this.serverSocketChannel=ServerSocketChannel.open();
            this.selector=Selector.open();
            this.serverSocketChannel.bind(new InetSocketAddress("127.0.0.1", PORT));
            //别忘了设置非阻塞，不然监听个毛的NIO
            this.serverSocketChannel.configureBlocking(false);
            //监听连接事件
            this.serverSocketChannel.register(this.selector, SelectionKey.OP_ACCEPT);
        } catch (IOException e) {
            log.error("构造服务端失败",e);
        }
    }

    /**
     * 监听读写事件
     */
    public void listen(){
        try{
            while(true){
                //获取监听事件总数,2秒一次
                int count=selector.select(2000);
                if(count>0){
                    Set<SelectionKey> selectionKeys= selector.selectedKeys();
                    Iterator<SelectionKey> iterator=selectionKeys.iterator();
                    while(iterator.hasNext()){
                        SelectionKey selectionKey=iterator.next();
                        //连接事件
                        if(selectionKey.isAcceptable()){
                            SocketChannel socketChannel=serverSocketChannel.accept();
                            socketChannel.configureBlocking(false);
                            //注册读取事件
                            socketChannel.register(selector,SelectionKey.OP_READ);
                            log.info("来自客户端{}连接成功",socketChannel.getRemoteAddress());
                        }
                        //读取事件
                        if(selectionKey.isReadable()){
                            readMsg(selectionKey);
                        }
                        iterator.remove();
                        log.info("   ");
                    }
                }else{
//                    log.info("等待....");
                }

            }
        }catch (Exception e){
            log.error("服务端监听异常",e);
        }
    }

    /**
     * 读取客户端消息
     * @param selectionKey
     */
    public void readMsg(SelectionKey selectionKey) {
        SocketChannel socketChannel=null;
        try{
            //selectKey获取channel
            socketChannel= (SocketChannel) selectionKey.channel();
            //创建缓冲区
            ByteBuffer byteBuffer=ByteBuffer.allocate(1024);
            //读取消息
            int count=socketChannel.read(byteBuffer);
            if(count>0){
                String msg=new String(byteBuffer.array(), CharsetUtil.UTF_8);
                log.info("读取客户端消息:{}",msg);
                //转发到对应客户端
                transferToClient(socketChannel,msg);
            }
        }catch (Exception e){
            try {
                log.info("{}离线",socketChannel.getRemoteAddress());
                //取消注册
                selectionKey.cancel();
                //关闭通道
                socketChannel.close();
            } catch (IOException ex) {
                log.error("关闭流异常",ex);
            }
        }
    }

    /**
     * 消息转发逻辑
     * 聊天室聊天是客户端到客户端，你放到服务端有个毛用
     * 这里遇到过一个坑，selector.keys和selector.selectedKeys()
     * selector.keys是所有注册到selector上的channelkey，例如这个程序一个服务端两个客户端，一共就是3个key
     * selector.selectedKeys是注册再selector上并且等待io操作channel的selectionKey，例如这个程序当前就一个read监听，拿到的就一个selectorKey
     * 每次remove就是去掉selectedKeys里的一个，不然监听accept事件，每次进来都有
     * SelectionKey在被轮询后需要remove()。注册过的channel信息会以SelectionKey的形式存储在selector.keys()中。也就是说每次select()后的selectedKeys迭代器中是不能还有成员的，但keys()中的成员是不会被删除的(以此来记录channel信息)。
     * selector不会自己删除selectedKeys()集合中的selectionKey，那么如果不人工remove()，将导致下次select()的时候selectedKeys()中仍有上次轮询留下来的信息，这样必然会出现错误。
     * ————————————————
     * @param transferSocketChannel 用于转发的channel
     * @param msg
     */
    public void transferToClient(SocketChannel transferSocketChannel, String msg) throws IOException {
        log.info("服务转发消息:{}", msg);
        for (SelectionKey selectionKey : selector.keys()) {
            //从选择器拿到channel
            Channel channel = selectionKey.channel();
            //选择器中的channel和读取信息用的channel不同，才会进行转发
            if (channel instanceof SocketChannel && channel != transferSocketChannel) {
                SocketChannel socketChannel = (SocketChannel) channel;
                //消息写入缓冲区
                socketChannel.write(ByteBuffer.wrap(msg.getBytes()));
            }
        }
    }

    public static void main(String[] args) {
        log.info("开始启动服务端");
        ChatServer server=new ChatServer();
        server.listen();
    }

}
