package com.lyn.codeLearing.netty.caseBokeyuan;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName HttpServerHandler
 * @Deacription TODO
 * @Author wrx
 * @Date 2022/6/13/013 17:14
 * @Version 1.0
 **/
@Slf4j
public class HttpServerHandler extends SimpleChannelInboundHandler<HttpObject> {

    private HttpRequest request;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HttpObject httpObject) throws Exception {
        if(httpObject instanceof HttpRequest) {
            request= (HttpRequest) httpObject;
            String url=request.getUri();
            log.info("url:{}",url);
        }

        if(httpObject instanceof HttpContent){
            HttpContent httpContent= (HttpContent) httpObject;
            ByteBuf buf =httpContent.content();
            log.info("传入内容:{}",buf.toString(CharsetUtil.UTF_8));

            ByteBuf byteBuf= Unpooled.copiedBuffer("hello world",CharsetUtil.UTF_8);

            FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, byteBuf);
            response.headers().add(HttpHeaderNames.CONTENT_TYPE, "text/plain");
            response.headers().add(HttpHeaderNames.CONTENT_LENGTH, byteBuf.readableBytes());

            ctx.writeAndFlush(response);
        }
    }
}
