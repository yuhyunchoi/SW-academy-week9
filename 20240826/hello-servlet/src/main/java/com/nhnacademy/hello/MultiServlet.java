package com.nhnacademy.hello;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;

public class MultiServlet extends HttpServlet {
    private static final Logger log = Logger.getLogger(MultiServlet.class.getName());

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] values = req.getParameterValues("class");
        String url = getServletContext().getInitParameter("url");

        try(PrintWriter out = resp.getWriter()) {
            out.println(String.join(",", values));
            out.printf("url:%s", url);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
