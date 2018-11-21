package com.servlet.module;


import com.servlet.bean.Request;
import com.servlet.bean.Response;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLStreamHandler;

import static com.servlet.bean.Server.WEB_ROOT;

/**
 * 处理对servlet资源的HTTP请求
 */
public class ServletProcessor1 {

    public void process(Request request, Response response){
        String uri =request.getUri();
        String servletNmae=uri.substring(uri.lastIndexOf("/")+1);
        URLClassLoader loader =null;
        try{
            URL [] urls =new URL[1];
            URLStreamHandler streamHandler=null;
            File classPath=new File(WEB_ROOT);

        }catch (Exception e){

        }



    }

    public static void main(String[] args) {
        Integer i =new Integer(4);
        System.out.println(i.toString().equals("4"));
    }


}
