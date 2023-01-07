package com.lyn.codeLearing.netty.caseFromAliyun;

import com.lyn.codeLearing.netty.caseFromAliyun.serverHandler.KimuraServerHandler;
import com.lyn.codeLearing.netty.caseFromAliyun.serverHandler.KimuraServerTaskQueueHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName 服务端
 * @Deacription 学习来源https://developer.aliyun.com/article/769587
 * @Author wrx
 * @Date 2022/6/20/020 10:21
 * @Version 1.0
 **/
@Slf4j
public class KimuraServer {

    public static void main(String[] args) throws Exception {
        /**
         * 两个核心线程组，里面是个线程池，每个EventLoopGroup中包含一个或多个EventLoop，每个EventLoop中维护一个Selector实例
         * bossGroup用于监听客户端连接，负责与客 户端创建连接，把连接注册到workgroup的selector中
         * workGroup用于处理每一个连接发生的读写事件
         * 默认线程数是cpu核数的两倍，自定义核数构造输入核数即可
         */
        EventLoopGroup bossGroup =new NioEventLoopGroup();
        EventLoopGroup workerGroup =new NioEventLoopGroup();

        try{
            /**
             *  创建启动器
             *  Bootstrap启动器的步骤：
             *  设置eventloopgroup线程组->设置通道类型->设置option参数->设置handler流水线->端口绑定->启动->等待通道关闭->优雅关闭
             *  client用Bootstrap，server用serverBootstrap
             */
            ServerBootstrap bootstrap=new ServerBootstrap();
            //设置boss和worker线程组
            bootstrap.group(bossGroup,workerGroup)
                     /**
                      *  服务端通道类型，通道类型有多个
                      *  NioSocketChannel:异步非阻塞的客户端tcp socket连接；NioServerSocketChannel：异步非阻塞的服务端tcp socket连接
                      *  OioSocketChannel：同步阻塞的客户端tcp socket连接；OioServerSocketChannel：同步阻塞的服务器端tcp socket连接
                      *  NioSctpChannel：异步的客户端sctp（stream control transmission protocol 流控制传输协议）；NioSctpServerChannel：异步服务端sctp
                      */
                     .channel(NioServerSocketChannel.class)
                     /**
                      * option设置服务端用于接收进来的连接，bossGroup线程
                      * SO_BACKLOG 服务端接收连接队列长度，队列已满则拒绝连接，默认值win200其他系统128
                      */
                     .option(ChannelOption.SO_BACKLOG,128)
                     /**
                      * childOption提供给父管道接收到的连接，workGroup线程
                      * SO_RCVBUF :tcp数据接收缓冲区大小
                      * TCP_NODELAY :立即发送数据，没有delay默认true
                      * SO_KEEPALIVE :连接保活，默认false，启动功能时，tcp会主动探测空闲连接的有效性
                      */
                     .childOption(ChannelOption.SO_KEEPALIVE,true)
                     /**
                      * 设置流水线-重中之重
                      * ChannelPipeline是Netty处理请求的责任链（流水线）
                      * ChannelHandler是具体处理请求的处理器，每一个channel都有自己的处理器
                      * 在bootstrap中的childHandler方法要初始化通道，实例化一个ChannelInitializer，重写intiChannel方法，装配流水线
                      */
                     .childHandler(new ChannelInitializer<SocketChannel>() {
                         @Override
                         protected void initChannel(SocketChannel socketChannel) throws Exception {
                             /**
                              * pipeline管道设置处理器，处理器自定义，处理接收/发送的信息
                              * 处理器handler有两种：channelInboundHandlerAdapter（入站处理器），channelOutBoundHandlerAdapter（出站处理器）
                              * 入站：数据从底层java nio channel到netty的channel
                              * 出站：通过netty的channel来操作底层的java nio channel
                              * --------------------------------------------------
                              * pipeline/channelPipeline
                              * channelHandler是一个流水线处理器，一个channel可能会有多个channelhandler，pipeline的作用就是把多个handler像流水线一样一个一个处理
                              * 可以把他理解成一个容器,一个channel中只有一个pipeline在channel初始化的时候就已经创建，所有channelhandler都会注册到这个pipeline中
                              * channel--->pipeline--->channelhandler--->channelhandler--->channelhandler....
                              */
                             socketChannel.pipeline().addLast(new KimuraServerHandler());
                         }
                     });
            log.info("服务端已启动....");
            /**
             *  绑定端口号，启动服务端，默认是异步，加上sync是同步
             *  ChannelFuture提供完成操作后的异步通知，在一般的socket编程中，等待响应结果是同步阻塞的，netty则不会造成阻塞
             *  channelFuture是采取类似观察者模式形式进行结果获取。
             */
            ChannelFuture channelFuture=bootstrap.bind(8088).sync();
            //添加监听器
            channelFuture.addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture channelFuture) throws Exception {
                    if(channelFuture.isSuccess()){
                        log.info("连接成功");
                    }else{
                        log.info("连接失败");
                    }
                }
            });

            //监听关闭通道
            channelFuture.channel().closeFuture().sync();
        }finally {
            /**
             * 优雅的关闭，关闭所有的child channel，释放掉底层资源
             */
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }

    }
}
