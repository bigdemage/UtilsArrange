package com.lyn.codeLearing.netty.caseFromAliyun.clientHandler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.Scanner;

/**
 * @ClassName KimuraClientHandler
 * @Deacription 自定义客户端处理器
 * @Author wrx
 * @Date 2022/6/20/020 14:51
 * @Version 1.0
 **/
@Slf4j
public class KimuraClientShouDongHandler extends ChannelInboundHandlerAdapter {

    /**
     * 发送消息到服务端
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        StringBuilder stringbuilder = new StringBuilder();
        Scanner scanner = new Scanner(System.in);
        while(true)
        {
            String text = scanner.nextLine().trim();
            if ("".equals(text))
            {
                break;
            }
            stringbuilder.append(text);
            ctx.writeAndFlush(Unpooled.copiedBuffer(text+"\n", CharsetUtil.UTF_8));
        }

    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //接收服务端返回消息
        ByteBuf byteBuf = (ByteBuf) msg;
        log.info("收到服务端{}发送的消息:{}", ctx.channel().remoteAddress(), byteBuf.toString(CharsetUtil.UTF_8));
    }
}
