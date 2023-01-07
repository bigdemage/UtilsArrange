package com.lyn.codeLearing.netty.caseFromAliyun.clientHandler;

import com.lyn.testCode.ReadInText;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName KimuraClientHandler
 * @Deacription 自定义客户端处理器
 * @Author wrx
 * @Date 2022/6/20/020 14:51
 * @Version 1.0
 **/
@Slf4j
public class KimuraClientHandler extends ChannelInboundHandlerAdapter {

    /**
     * 发送消息到服务端
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        String filePath = "SensitiveWord.txt";
        Set<String> set = ReadInText.readTxt(filePath);
        Iterator iterator = set.iterator();
        AtomicInteger index=new AtomicInteger(1);
        while (iterator.hasNext()) {
            String msg = (String) iterator.next();
            log.info("计数器:{}",index.getAndIncrement());
            if(index.get()==5){
                return;
            }
            ctx.writeAndFlush(Unpooled.copiedBuffer(msg+"\n", CharsetUtil.UTF_8));
//            int sleepSeconds = RandomUtils.nextInt(2000, 6000);
//            Thread.sleep(sleepSeconds);
        }
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //接收服务端返回消息
        ByteBuf byteBuf = (ByteBuf) msg;
        log.info("收到服务端{}发送的消息:{}", ctx.channel().remoteAddress(), byteBuf.toString(CharsetUtil.UTF_8));
    }
}
