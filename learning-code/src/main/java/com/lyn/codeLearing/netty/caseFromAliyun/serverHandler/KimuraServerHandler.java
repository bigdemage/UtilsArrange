package com.lyn.codeLearing.netty.caseFromAliyun.serverHandler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName KimuraServerHandler
 * @Deacription 自定义管道处理器
 *              需要集成netty的handlerAdapter
 *              常用事件：
 *                  注册事件 channelRegistered。
 *                  连接建立事件 channelActive。
 *                  读事件和读完成事件 channelRead、fireChannelReadComplete。
 *                  异常通知事件 exceptionCaught。
 *                  用户自定义事件 userEventTriggered。
 *                  Channel可写状态变化事件 ChannelWritabilityChanged
 *                  连接关闭事件 channelInactive
 *              ----------------------------------------------------------
 *              Channel：连接到网络套接字/能进行读、写、连接、绑定等I/O操作的组件
 *                       通道当前状态/channel配置参数/channel支持的io操作以及处理与channel相关联的所有io事件和请求的channelPipeline
 *              ----------------------------------------------------------
 *
 * @Author wrx
 * @Date 2022/6/20/020 11:15
 * @Version 1.0
 **/
@Slf4j
public class KimuraServerHandler extends ChannelInboundHandlerAdapter {

    /**
     * 处理客户端发送来的消息
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf= (ByteBuf) msg;
        log.info("收到客户端{}发送的消息：{}",ctx.channel().remoteAddress(),byteBuf.toString(CharsetUtil.UTF_8));
    }

    /**
     * 处理返回消息
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(Unpooled.copiedBuffer("服务端已接到信息",CharsetUtil.UTF_8));
    }

    /**
     * 异常处理
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.error("服务端发生异常",cause);
        ctx.close();
    }
}
