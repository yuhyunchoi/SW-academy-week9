package com.nhnacademy.student.servlet;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

import static javax.servlet.RequestDispatcher.*;

@Deprecated
@Slf4j
@WebServlet(name = "errorServlet", urlPatterns = "/error")
public class ErrorServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("status_code", req.getAttribute(ERROR_STATUS_CODE));
        req.setAttribute("exception_type", req.getAttribute(ERROR_EXCEPTION_TYPE));
        req.setAttribute("message", req.getAttribute(ERROR_MESSAGE));
        req.setAttribute("exception", req.getAttribute(ERROR_EXCEPTION));
        req.setAttribute("request_uri", req.getAttribute(ERROR_REQUEST_URI));

        log.error("ERROR_STATUS_CODE: {}", req.getAttribute(ERROR_STATUS_CODE));
        log.error("ERROR_EXCEPTION_TYPE: {}", req.getAttribute(ERROR_EXCEPTION_TYPE));
        log.error("ERROR_MESSAGE: {}", req.getAttribute(ERROR_MESSAGE));
        log.error("ERROR_EXCEPTION: {}", req.getAttribute(ERROR_EXCEPTION));
        log.error("ERROR_REQUEST_URI: {}", req.getAttribute(ERROR_REQUEST_URI));

        RequestDispatcher rd = req.getRequestDispatcher("error.jsp");
        rd.forward(req, resp);

    }
}
