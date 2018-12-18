package com.lyn.codeLearing.netty.case3Version4x.server;


import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * 服务端
 */
public class HelloServer {
    private static final int port=10010;


    public static void main(String[] args) throws  Exception{
        //EventLoopGroup用于管理channel
        EventLoopGroup boss =new NioEventLoopGroup();
        EventLoopGroup work =new NioEventLoopGroup();

        try{
            //启动器
            ServerBootstrap b =new ServerBootstrap();
            //绑定group
            b.group(boss,work);
            //设置管道
            b.channel(NioServerSocketChannel.class);
//            b.handler(new LoggingHandler(LogLevel.ERROR));
            /**
             *  规则过滤器  责任链路 一个新的连接进来时，就会创建一个channel
             *  而这个Initializer就会把handler实例添加到该channel的pipeline中
             *  这个handler会收到有关入站的信息
             */
            b.childHandler(new HelloServerInitializer());
            //服务器绑定接口监听
            //sync()方法的调用将导致当前 Thread阻塞，一直到绑定操作完成为止
            ChannelFuture f=b.bind(port).sync();
            //阻塞当前线程直到它关闭
            f.channel().closeFuture().sync();

        }finally {
            //关闭管理，不用担心内存泄露，释放所有资源
            boss.shutdownGracefully();
            work.shutdownGracefully();
        }

    }

}
