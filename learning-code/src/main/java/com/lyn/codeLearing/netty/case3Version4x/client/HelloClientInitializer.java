package com.lyn.codeLearing.netty.case3Version4x.client;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

public class HelloClientInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    public void initChannel(SocketChannel socketChannel) throws Exception {

        /**
         * 配置必须与服务端一样
         */
        ChannelPipeline pipeline =socketChannel.pipeline();

        /**
         * 帧解码器
         * param1：最大帧的长度
         * param2：定义分隔符
         * 在Delimiters中提供给我们两种分隔符。一种是“0x00-NUL”分隔符。另外一种就是实例中使用的“\r\n”或“\n”分隔符。
         * 当分隔符是\n的时候，框架默认解码器为基于行的帧解码器（LineBaseFrameDecoder），否则按照可读取比特长独进行帧解码
         */
        pipeline.addLast("framer",new DelimiterBasedFrameDecoder(8192, Delimiters.lineDelimiter()));
        /**
         * 解码器：将比特流转换为默认编码的字符串，默认编码为UTF-8
         */
        pipeline.addLast("decoder",new StringDecoder());
        /**
         * 编码器：将字符串转换成Byte[]
         */
        pipeline.addLast("encoder",new StringEncoder());
        //代码逻辑handler
        pipeline.addLast("handler",new HelloClientHandler());


    }
}
