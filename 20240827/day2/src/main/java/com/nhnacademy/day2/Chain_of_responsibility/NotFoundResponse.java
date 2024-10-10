package com.nhnacademy.day2.Chain_of_responsibility;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class NotFoundResponse extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");

        try(PrintWriter out = resp.getWriter() ) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println(    "<title>404 Not Found</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>존재하지 않는 페이지!</h1>");
            out.println("</body>");
        }catch(Exception e) {
            throw new RuntimeException(e);
        }


    }
}
