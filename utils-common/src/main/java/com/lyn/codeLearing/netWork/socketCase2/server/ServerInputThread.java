package com.lyn.codeLearing.netWork.socketCase2.server;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

/**
 * 线程---服务端读取
 */
public class ServerInputThread extends Thread{

    private Socket socket;

    public ServerInputThread(Socket socket){
        this.socket=socket;
    }


    @Override
    public void run() {
        try {

            while(true){
                InputStream is =socket.getInputStream();

                byte[] bb =new byte[2048];

                int length=is.read(bb);

                String str=new String(bb,0,length);

                System.out.println(str);
            }



        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
