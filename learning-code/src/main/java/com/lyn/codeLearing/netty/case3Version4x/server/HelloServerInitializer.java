package com.lyn.codeLearing.netty.case3Version4x.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;


/**
 * 规则
 * DelimiterBasedFrameDecoder Netty在官方网站上提供的示例显示 有这么一个解码器可以简单的消息分割。
 * 其次 在decoder里面我们找到了String解码编码器。着都是官网提供给我们的。
 * 主要设置字节解码器、和代码处理逻辑
 */
public class HelloServerInitializer extends ChannelInitializer<SocketChannel>{
    @Override
    public void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline=socketChannel.pipeline();
        //帧解码器
        pipeline.addLast("framer",new DelimiterBasedFrameDecoder(8192, Delimiters.lineDelimiter()));
        //编码、解码
        pipeline.addLast("decoder",new StringDecoder());
        pipeline.addLast("encoder",new StringEncoder());
        //代码逻辑handler
        pipeline.addLast("handler", new HelloServerHandler());

    }
}
