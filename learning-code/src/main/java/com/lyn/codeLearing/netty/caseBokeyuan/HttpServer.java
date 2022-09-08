package com.lyn.codeLearing.netty.caseBokeyuan;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @ClassName HttpServer
 * @Deacription TODO
 * @Author wrx
 * @Date 2022/6/13/013 15:50
 * @Version 1.0
 **/
public class HttpServer {

    public static void main(String[] args) {
        EventLoopGroup boss =new NioEventLoopGroup();
        EventLoopGroup worker =new NioEventLoopGroup();

        try{
            ServerBootstrap bootstrap=new ServerBootstrap();
            bootstrap.group(boss,worker)
                 .channel(NioServerSocketChannel.class)
                 //自定义责任链
                 .childHandler(new HttpServerInitializer());
            //绑定端口号
            ChannelFuture future=bootstrap.bind(8080).sync();

            //等待关闭
            future.channel().closeFuture().sync();

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            boss.shutdownGracefully();
            worker.shutdownGracefully();
        }

    }
}
