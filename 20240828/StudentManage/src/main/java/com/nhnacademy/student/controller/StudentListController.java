package com.nhnacademy.student.controller;

import com.nhnacademy.student.student.Student;
import com.nhnacademy.student.student.StudentRepository;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Slf4j
@RequestMapping(value="/student/list.do", method = RequestMapping.Method.GET)
public class StudentListController implements Command{

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp){
        StudentRepository studentRepository = (StudentRepository) req.getServletContext().getAttribute("studentRepository");
        List<Student> studentList = studentRepository.getStudents();

        req.setAttribute("studentList", studentList);
        return "/student/list.jsp";
    }

}
