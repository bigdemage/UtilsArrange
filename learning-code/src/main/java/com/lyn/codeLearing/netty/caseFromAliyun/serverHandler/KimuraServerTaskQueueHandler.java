package com.lyn.codeLearing.netty.caseFromAliyun.serverHandler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName KimuraServerTaskQueueHandler
 * @Deacription TODO
 * @Author wrx
 * @Date 2022/6/20/020 16:02
 * @Version 1.0
 **/
@Slf4j
public class KimuraServerTaskQueueHandler extends ChannelInboundHandlerAdapter{


    /**
     * 对于长时间的业务逻辑，使用业务队列异步处理
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        //获取到线程池eventLoop，添加线程，执行
        ctx.channel().eventLoop().execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    ByteBuf wushuang = (ByteBuf) msg;
                    log.info("等了两秒处理完毕-消息：{}",wushuang.toString(CharsetUtil.UTF_8));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

//        //方式二，延迟性队列,延迟多久再执=
//        ctx.channel().eventLoop().schedule(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    Thread.sleep(2000);
//                    ByteBuf wushuang = (ByteBuf) msg;
//                    log.info("等了两秒处理完毕-消息：{}",wushuang.toString(CharsetUtil.UTF_8));
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        },10,TimeUnit.SECONDS);
    }
}
