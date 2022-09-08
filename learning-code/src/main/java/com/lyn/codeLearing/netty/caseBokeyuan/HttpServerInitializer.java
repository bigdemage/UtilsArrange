package com.lyn.codeLearing.netty.caseBokeyuan;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

/**
 * @ClassName HttpServerInitializer
 * @Deacription 链式结构，就是跟链条一样一个一个处理
 * @Author wrx
 * @Date 2022/6/13/013 16:18
 * @Version 1.0
 **/
public class HttpServerInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        //管道
        ChannelPipeline pipeline=socketChannel.pipeline();
        //http编解码器codec
        //HttpServerCodec 是 HttpRequestDecoder 和 HttpResponseEncoder 的组合
        //替换成两个也可pipeline.addLast("httpResponseEndcoder", new HttpResponseEncoder());pipeline.addLast("HttpRequestDecoder", new HttpRequestDecoder());
        pipeline.addLast("httpServerCodec",new HttpServerCodec());
        //自定义channelHandler
        pipeline.addLast("httpServerHandler",new HttpServerHandler());

    }
}

