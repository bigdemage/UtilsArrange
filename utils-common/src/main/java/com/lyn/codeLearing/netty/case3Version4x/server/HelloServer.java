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
        EventLoopGroup boss =new NioEventLoopGroup();
        EventLoopGroup work =new NioEventLoopGroup();

        try{
            ServerBootstrap b =new ServerBootstrap();
            b.group(boss,work);
            b.channel(NioServerSocketChannel.class);
            b.childHandler(new HelloServerInitializer());//规则过滤器
            //服务器绑定接口监听
            ChannelFuture f=b.bind(port).sync();
            //监听服务器关闭监听
            f.channel().closeFuture().sync();

        }finally {
            boss.shutdownGracefully();
            work.shutdownGracefully();
        }

    }

}
