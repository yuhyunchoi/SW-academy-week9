package com.nhnacademy.student.controller;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequestMapping(value = "/student/register/jsp", method = RequestMapping.Method.GET)
public class StudentRegisterFormController implements Command{

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse res) {
        return "/student/register.jsp";
    }
}
