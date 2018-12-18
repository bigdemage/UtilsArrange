package com.lyn.codeLearing.netWork.socketCase2.server;


import java.net.ServerSocket;
import java.net.Socket;

/**
 * 服务端
 * 用线程接收/输出
 * 服务端始终监听状态
 */
public class MainServer {

    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(4000);


        while (true) {

            Socket socket = serverSocket.accept();

            //启动读写线程
            Thread inputThread = new ServerInputThread(socket);
            Thread outputThread = new ServerOutputThread(socket);

            inputThread.start();
            outputThread.start();


        }
    }
}
