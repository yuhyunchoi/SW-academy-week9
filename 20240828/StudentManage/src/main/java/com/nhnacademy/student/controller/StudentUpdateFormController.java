package com.nhnacademy.student.controller;

import com.nhnacademy.student.student.Gender;
import com.nhnacademy.student.student.Student;
import com.nhnacademy.student.student.StudentRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

@RequestMapping(value = "/student/register.do", method = RequestMapping.Method.GET)
public class StudentUpdateFormController implements Command {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse res){
        return "/student/register.jsp";
    }
}
