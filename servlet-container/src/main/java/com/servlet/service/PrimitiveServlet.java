package com.servlet.service;

import javax.servlet.*;
import java.io.IOException;
import java.io.PrintWriter;


/**
 * 重载servlet
 */
public class PrimitiveServlet implements Servlet {


    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        System.out.println("initServlet");
    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {

        System.out.println("from service");

        PrintWriter printWriter=servletResponse.getWriter();

        printWriter.println("Hello myselfServlet");

    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {
        System.out.println("destroy Myservlet");

    }
}
