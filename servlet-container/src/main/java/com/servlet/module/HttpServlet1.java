package com.servlet.module;


import com.servlet.bean.Request;
import com.servlet.bean.Response;
import com.servlet.bean.ServletProcessor;
import com.servlet.bean.StaticResourceProcessor;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServlet1 {


    private final static String SHUTDOWN_COMMAND = "/SHOTDOWN";

    private boolean shutdown = false;

    public void await() {

        ServerSocket serverSocket = null;
        int port = 8080;
        try {
            //套接字绑定
            serverSocket = new ServerSocket(port, 1, InetAddress.getByName("127.0.0.1"));
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
        while (!shutdown) {
            Socket socket = null;
            InputStream inputStream = null;
            OutputStream outputStream = null;
            try {
                socket = serverSocket.accept();
                inputStream = socket.getInputStream();
                outputStream = socket.getOutputStream();


                Request request = new Request(inputStream);
                request.parse();

                Response response = new Response(outputStream);
                response.setRequest(request);

                if (request.getUri().startsWith("/servlet/")) {
                    ServletProcessor processor = new ServletProcessor();
                    processor.process(request, response);
                } else {
                    StaticResourceProcessor staticResourceProcessor = new StaticResourceProcessor();

                    staticResourceProcessor.process(request, response);
                }

                socket.close();//关闭socket
                shutdown = request.getUri().equals(SHUTDOWN_COMMAND);


            } catch (Exception e) {

            }
        }

    }

    public static void main(String[] args) {
        HttpServlet1 server =new HttpServlet1();
        server.await();

    }

}
