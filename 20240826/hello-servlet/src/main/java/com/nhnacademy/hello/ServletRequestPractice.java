package com.nhnacademy.hello;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;

public class ServletRequestPractice extends HttpServlet {
    private static final Logger log = Logger.getLogger(ServletRequestPractice.class.getName());

    @Override
    protected void doPost(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/plain");
        resp.setCharacterEncoding("utf-8");

        try(PrintWriter writer = resp.getWriter()) {
            writer.println("locale=" + req.getLocalAddr());
            writer.println("parameter name=" + req.getParameter("userId"));
            writer.println("content type=" + req.getContentType());
            writer.println("content length=" + req.getContentLengthLong());
            writer.println("method=" + req.getMethod());
            writer.println("servlet=" + req.getServletPath());
            writer.println("request uri=" + req.getRequestURI());
            writer.println("request url=" + req.getRequestURL());
            writer.println("User-Agent=" + req.getHeader("User-Agent"));
        }catch(Exception e) {
            throw new RuntimeException(e);
        }
    }
}
