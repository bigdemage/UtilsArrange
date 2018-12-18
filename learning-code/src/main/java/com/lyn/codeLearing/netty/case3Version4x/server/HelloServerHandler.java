package com.lyn.codeLearing.netty.case3Version4x.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.net.InetAddress;

/**
 * 代码逻辑
 * SimpleChannelInboundHandler是官网推荐的
 *
 */
public class HelloServerHandler extends SimpleChannelInboundHandler<String> {
    /**
     * 每一次收到消息时触发
     * 注意:字符串最后面的"\n"是必须的。因为我们在前面的解码器DelimiterBasedFrameDecoder是一个根据字符串结尾为“\n”来结尾的。假如没有这个字符的话。解码会出现问题。！！！
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        //打印收到的消息
        System.out.println(ctx.channel().remoteAddress()+"  receive:" +msg);
        //返回客户端消息
        //writeAndFlush：写入Buffer并刷入
        ctx.writeAndFlush("I already receive you msg! \n");
    }


    /**
     * 覆盖channelActive方法，在channel被调用的时候触发(建立连接的时候)
     * 当连接活跃（建立）的时候触发
     * 当一个新的连接建立时触发
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("RemoteAddress:"+ctx.channel().remoteAddress()+"   active!!!");

        ctx.writeAndFlush("Welcome to "+ InetAddress.getLocalHost().getHostName()+" service! \n");

        super.channelActive(ctx);
    }

    /**
     * 异常时被调用
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("Netty服务异常:");
        cause.printStackTrace();
        super.exceptionCaught(ctx, cause);
    }
}
