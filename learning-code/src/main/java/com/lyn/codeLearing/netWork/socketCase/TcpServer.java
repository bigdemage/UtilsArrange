package com.lyn.codeLearing.netWork.socketCase;


import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 服务端
 */
public class TcpServer {

    public static void main(String[] args) throws Exception {
        //绑定端口号
        ServerSocket ss = new ServerSocket(8888);
        //等待客户端连接
        Socket socket = ss.accept();

        //接收客户端信息
        InputStream is = socket.getInputStream();

        //向客户端发送信息
        OutputStream os = socket.getOutputStream();

        byte[] bb = new byte[200];

        int length=is.read(bb);

        System.out.println(new String(bb,0,length));
//        while (-1 != (length = is.read(bb, 0, bb.length))) {
//            String str = new String(bb, 0, length);
//
//            System.out.println(str);
//        }

        os.write("mdzz you good too".getBytes());

        os.close();
        is.close();
        socket.close();

    }
}
