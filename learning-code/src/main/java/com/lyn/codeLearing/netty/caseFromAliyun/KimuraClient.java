package com.lyn.codeLearing.netty.caseFromAliyun;

import com.lyn.codeLearing.netty.caseFromAliyun.clientHandler.KimuraClientShouDongHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName KimuraClient
 * @Deacription 客户端
 * @Author wrx
 * @Date 2022/6/20/020 13:48
 * @Version 1.0
 **/
@Slf4j
public class KimuraClient {
    public static void main(String[] args) throws InterruptedException {
        NioEventLoopGroup eventLoopGroup=new NioEventLoopGroup();
        try{
            Bootstrap bootstrap=new Bootstrap();
            //设置线程组
            bootstrap.group(eventLoopGroup)
                     //客户端通道实现类型
                     .channel(NioSocketChannel.class)
                     .handler(new ChannelInitializer<SocketChannel>() {
                         @Override
                         protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new KimuraClientShouDongHandler());
                         }
                     });
            log.info("客户端已启动.....");
            //连接服务器
            ChannelFuture channelFuture=bootstrap.connect("127.0.0.1",8088).sync();
            //监听关闭
            channelFuture.channel().closeFuture().sync();
        }finally {
            //关闭线程组
            eventLoopGroup.shutdownGracefully();
        }
    }
}
