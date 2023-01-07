package com.lyn.codeLearing.netWork.socketCase;



import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 *
 * 客户端
 */
public class TcpClient {

    public static void main(String[] args) throws Exception{

        Socket socket=new Socket("localhost",8888);

        //接收服务端消息
        InputStream is =socket.getInputStream();

        OutputStream os =socket.getOutputStream();

        os.write("helloMdzz".getBytes());

        byte[] bb =new byte[200];

        int length=is.read(bb);

        System.out.println(new String(bb,0,length));
//        while(-1!=(length=is.read(bb,0,bb.length))){
//            String str=new String(bb,0,length);
//
//            System.out.println(str);
//        }


        os.close();
        is.close();
        socket.close();

    }
}
