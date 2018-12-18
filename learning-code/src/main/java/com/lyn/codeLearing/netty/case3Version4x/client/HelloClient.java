package com.lyn.codeLearing.netty.case3Version4x.client;


import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 客户端
 */
public class HelloClient {

    private static final int port=10010;
    private static final String host ="127.0.0.1";

    public static void main(String[] args) throws Exception{

        EventLoopGroup group=new NioEventLoopGroup();

        try{
            Bootstrap b =new Bootstrap();
            b.group(group).channel(NioSocketChannel.class).handler(new HelloClientInitializer());
            //连接服务端
            Channel ch=b.connect(host,port).sync().channel();
            //控制台输入
            BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
            while(true){
                String str=in.readLine();
                if(StringUtils.isBlank(str)){
                    break;
                }
                /**
                 * 向服务端发送在控制台输入的文本 用\r\n结尾
                 * 用\r\n结尾是因为在handler中添加了DelimiterBasedFrameDecoder帧解码
                 * 这个解码器是一个根据\n符号位分隔符的解码器，所以每条信息的最后必须加上\n否则无法识别和解码
                 */
                ch.writeAndFlush(str+"\r\n");
            }
        }finally {
            group.shutdownGracefully();
        }

    }

}
