package com.nhnacademy.hello;

import org.jsoup.Jsoup;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;

public class BeautifyServlet extends HttpServlet{
    private static final Logger log = Logger.getLogger(BeautifyServlet.class.getName());

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String html = req.getParameter("html");
        resp.setContentType("text/plain");
        resp.setCharacterEncoding("UTF-8");

        try(PrintWriter out = resp.getWriter()){

            out.println(Jsoup.parse(html));
        }catch(Exception e){
            log.info(e.getMessage());
        }
    }
}
