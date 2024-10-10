package com.nhnacademy.student.controller;

import com.nhnacademy.student.student.Gender;
import com.nhnacademy.student.student.Student;
import com.nhnacademy.student.student.StudentRepository;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Objects;

@RequestMapping(value = "/student/update.do", method = RequestMapping.Method.POST)
public class StudentUpdateController implements Command{

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp)  {
        StudentRepository studentRepository = (StudentRepository) req.getServletContext().getAttribute("studentRepository");
        try {
            req.setCharacterEncoding("UTF-8");
            String id = req.getParameter("id");
            String name = req.getParameter("name");
            Gender gender = Gender.valueOf(req.getParameter("gender"));
            int age = Integer.parseInt(req.getParameter("age"));
            if(Objects.isNull(id) || Objects.isNull(name) || age < 0){
                throw new RuntimeException("parameter is null or empty");
            }
            Student student = new Student(id, name, gender, age);
            studentRepository.update(student);

            return "redirect:/student/update.do?id=" + req.getParameter("id");

        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }

    }
}
