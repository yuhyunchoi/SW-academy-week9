package com.nhnacademy.student.servlet;

import com.nhnacademy.student.controller.*;
import com.nhnacademy.student.iniailizer.ControllerFactory;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

import static javax.servlet.RequestDispatcher.*;
import static javax.servlet.RequestDispatcher.ERROR_REQUEST_URI;

@Slf4j
@WebServlet(name = "frontServlet", urlPatterns = "*.do")
public class FrontServlet extends HttpServlet {

    private static final String REDIRECT_PREFIX = "redirect";

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        try {
            Command command = resolveCommand(req.getMethod(), req.getRequestURI());
//            ControllerFactory cf = (ControllerFactory) req.getServletContext().getAttribute("controllerFactory");
//            Command command = (Command) cf.getBean(req.getMethod(), req.getRequestURI());

            if (Objects.isNull(command)) {
                resp.sendError(404);
            }
            String view = command.execute(req, resp);
//            TimeProxy proxy = new TimeProxy(command);
//            String view = proxy.execute(req, resp);


            if (view.startsWith(REDIRECT_PREFIX)) {
                String redirectUrl = view.substring(REDIRECT_PREFIX.length() + 1);
                resp.sendRedirect(redirectUrl);
            }else{
                RequestDispatcher rd = req.getRequestDispatcher(view);
                rd.include(req, resp);
            }
        }catch(Exception e){
            req.setAttribute("status_code", req.getAttribute(ERROR_STATUS_CODE));
            req.setAttribute("exception_type", req.getAttribute(ERROR_EXCEPTION_TYPE));
            req.setAttribute("message", req.getAttribute(ERROR_MESSAGE));
            req.setAttribute("exception", req.getAttribute(ERROR_EXCEPTION));
            req.setAttribute("request_uri", req.getAttribute(ERROR_REQUEST_URI));
            log.error("status_code:{}", req.getAttribute(ERROR_STATUS_CODE));
            RequestDispatcher rd = req.getRequestDispatcher("/error.jsp");
            rd.forward(req, resp);
        }

    }

    private Command resolveCommand(String method, String path) {
        Command command = null;
        if("POST".equalsIgnoreCase(method) && path.equals("/student/delete.do") ){
            command = new StudentDeleteController();
        }else if("GET".equalsIgnoreCase(method) && path.equals("/student/list.do")){
            command = new StudentListController();
        }else if("GET".equalsIgnoreCase(method) && path.equals("/student/register.do")){
            command = new StudentRegisterFormController();
        }else if("POST".equalsIgnoreCase(method) && path.equals("/student/register.do")){
            command = new StudentRegisterController();
        }else if("GET".equalsIgnoreCase(method) && path.equals("/student/update.do")){
            command = new StudentUpdateFormController();
        }else if("POST".equalsIgnoreCase(method) && path.equals("/student/update.do")){
            command  = new StudentUpdateController();
        }else if("GET".equalsIgnoreCase(method) && path.equals("/student/view.do")){
            command = new StudentViewController();
        }
        return command;
    }
}
