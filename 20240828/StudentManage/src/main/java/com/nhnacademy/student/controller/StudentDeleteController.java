package com.nhnacademy.student.controller;

import com.nhnacademy.student.student.StudentRepository;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@RequestMapping(value ="/student/delete.do", method = RequestMapping.Method.POST)
public class StudentDeleteController implements Command{

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse res) {
        StudentRepository studentRepository = (StudentRepository) req.getServletContext().getAttribute("studentRepository");
        String id = req.getParameter("id");
        studentRepository.deleteById(id);

        return "redirect:/student/list.do";
    }



}
