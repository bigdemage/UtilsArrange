package com.lyn.codeLearing.netty.case1;


import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 服务类
 */
public class NettyServer {

    public static void main(String[] args) {


        ServerBootstrap bootstrap =new ServerBootstrap();

        ExecutorService boss= Executors.newCachedThreadPool();
        ExecutorService worker= Executors.newCachedThreadPool();

        //设置niosocket工厂
        bootstrap.setFactory(new NioClientSocketChannelFactory(boss,worker));
        //设置管道工厂
        bootstrap.setPipelineFactory(new ChannelPipelineFactory() {
            @Override
            public ChannelPipeline getPipeline() throws Exception {
                ChannelPipeline channelPipeline=Channels.pipeline();
                channelPipeline.addLast("",null);
                return null;
            }
        });

    }

}
