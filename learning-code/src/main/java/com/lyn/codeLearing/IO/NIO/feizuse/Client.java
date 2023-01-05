package com.lyn.codeLearing.IO.NIO.feizuse;

import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;

@Slf4j
public class Client {
    public static void main(String[] args) {

        try(SocketChannel socketChannel =SocketChannel.open()){
            socketChannel.connect(new InetSocketAddress("localhost",8080));
            log.info("waiting....");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
