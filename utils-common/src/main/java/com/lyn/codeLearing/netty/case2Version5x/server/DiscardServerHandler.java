package com.lyn.codeLearing.netty.case2Version5x.server;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.CharsetUtil;
import io.netty.util.ReferenceCountUtil;


/**
 * 服务处理通道，建立相应的规则
 * ChannelHandlerAdapter这个类实现了ChannelHandler接口，ChannelHandler提供了许多事件处理的接口方法
 *
 */
public class DiscardServerHandler extends ChannelHandlerAdapter {




    /**
     * 收到信息时被调用的方法，用于读取
     * @param ctx  通道处理的上下文
     * @param msg  接受到的消息
     * @throws Exception
     */
//    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf in = (ByteBuf) msg;
        //打印传过来的字符
        System.out.println(in.toString(CharsetUtil.UTF_8));
        /**
         * ByteBuf是一个引用计数对象，这个对象必须显示地调用release方法来释放
         * 处理器的职责是释放所有传递到处理器的引用计数对象
         */
        ReferenceCountUtil.release(msg);

    }

    /**
     * 发生异常时触发
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        /**
         * exceptionCaught事件处理方法是当出现Throwable对象才会调用
         * 当netty由于io错误或者处理器在处理事件时抛出异常，在大部分情况下，捕获的异常应该被记录下来，并且把关联的channel关闭掉
         * 然而这个方法的处理方式会在遇到不同异常情况下有不同的实现，比如可能在关闭连接之前发送一个错误码的响应信息，出现异常就
         */
        //打印异常信息
        cause.printStackTrace();
        //关闭上下文
        ctx.close();
    }
}
