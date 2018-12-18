package com.lyn.codeLearing.netWork.socketCase2.client;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

/**
 * 线程---客户端输出
 */
public class ClientOutputThread extends  Thread{

    private Socket socket;

    public ClientOutputThread(Socket socket){
        this.socket=socket;
    }

    @Override
    public void run() {

        try {

            while (true){
                OutputStream os =socket.getOutputStream();

                BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));

                String line =reader.readLine();

                os.write(line.getBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
