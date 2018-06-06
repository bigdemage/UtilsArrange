package com.lyn.codeLearing.netty.case2Version4x.server;


import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;


/**
 * 应用规则的服务端
 */
public class DiscardServer {

    private int port;

    public DiscardServer(int port){
        this.port=port;
    }

    public void run() throws Exception{

        /**
         * NioEventLoopGroup是用来处理I/O操作的多线程事件循环器
         * Netty提供了许多不同的EventLoopGroup的实现用来处理不同传输协议
         * 第一个经常叫做boss 用于接收进来的连接
         * 第二个经常叫做worker 用来处理已经接收的连接，一旦boss接收到连接，就会把连接信息注册到worker上
         * 如何知道多少个线程已经被使用，如何映射已经创建的chennels上都需要依赖于EventLoopGroup的实现
         * 并且可以通过构造函数来配置他们的关系
         */

        EventLoopGroup bossGroup=new NioEventLoopGroup();
        EventLoopGroup workGroup=new NioEventLoopGroup();
        System.out.println("准备运行端口："+port);

        try{


        /**
         * 启动NIO服务的辅助启动类 在这个服务中直接使用Channel
         */
        ServerBootstrap b =new ServerBootstrap();
        /**
         * 一定要设置group，不然会报异常group not set
         */
        b =b.group(bossGroup,workGroup);
        /**
         * ServerSocketChannel以NOI的selector（选择器）为基础进行实现的，用来接收新的连接
         */
        b=b.channel(NioServerSocketChannel.class);
        /**
         * 事件处理类经常会被用来处理一个最近的已经接收的channel，channelInitializer是一个特殊的处理类
         * 他的目的是帮助使用者配置一个新的channel
         * 也许你想通过增加一些处理类比如NettyServerHandler来配置一个新的channel
         * 或者其对应的ChannelPipeline来实现你的网络程序，当你的程序变得复杂时，可能你会增加更多的处理类到pipeline上
         * 然后提取这些匿名类到最顶层的类上
         */
        b=b.childHandler(new ChannelInitializer<SocketChannel>() {
            @Override
            public void initChannel(SocketChannel socketChannel) throws Exception {
                socketChannel.pipeline().addLast(new DiscardServerHandler());
            }
        });
        /**
         * 设置制定的通道实现配置参数。现在是写一个TCP/IP的服务端
         * 因此被允许这只socket的参数选项比如tcpNoDelay和keepAlive
         * 参考CannelOption和详细的channelConfig实现的接口文档以此可以对ChannelOptions有一个大概的认识
         */
        b=b.option(ChannelOption.SO_BACKLOG,128);
        /**
         * option是提供给NioServerSocketChannel用来接收进来的连接
         * childOption是提供给父管道ServerChannel接收到的连接
         */
        b=b.childOption(ChannelOption.SO_KEEPALIVE,true);
        //接口绑定并且启动接收进来的连接
        ChannelFuture f =b.bind(port).sync();
        //一直等待，知道socket被关闭
        f.channel().closeFuture().sync();
        }finally {
            //关闭group
            workGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws Exception {
        int port=10010;
        new DiscardServer(port).run();
        System.out.println("server is running");
    }

}
