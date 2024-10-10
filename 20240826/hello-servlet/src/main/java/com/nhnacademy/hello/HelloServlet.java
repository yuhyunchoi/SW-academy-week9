package com.nhnacademy.hello;

import java.io.*;
import java.util.Objects;
import java.util.logging.Logger;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class HelloServlet extends HttpServlet {
    private static final Logger log = Logger.getLogger(HelloServlet.class.getName());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        String title = getServletConfig().getInitParameter("title");
        String name = getServletConfig().getInitParameter("name");

        if(Objects.isNull(title)){
            title = "Mr.";
        }
        if(Objects.isNull(name)){
            name = "marco";
        }

        response.setCharacterEncoding("utf-8");
        try(PrintWriter writer = response.getWriter()){
            writer.println("<!DOCTYPE html>");
            writer.println("<html>");
            writer.println(     "<head>");
            writer.println(         "<meta charset=\"utf-8\">");
            writer.println(     "</head>");
            writer.println(     "<body>");
            writer.println(         "<h1>hello servlet!</h1>");
            writer.println(         "<h1>안녕 서블릿!</h1>");
            writer.printf(         "<h1>hello %s %s </h1>\n", title, name);
            writer.println(     "</body>");
            writer.println("</html>");
        }catch(IOException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        log.info("before init!");
        super.init(config);
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        log.info("before service!");
        super.service(req, resp);
    }

    @Override
    public void destroy(){
        log.info("before destory!");
        super.destroy();
    }
}