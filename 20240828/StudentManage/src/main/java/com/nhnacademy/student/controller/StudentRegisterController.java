package com.nhnacademy.student.controller;

import com.nhnacademy.student.student.Gender;
import com.nhnacademy.student.student.Student;
import com.nhnacademy.student.student.StudentRepository;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

@Slf4j
@RequestMapping(value = "/student/list.do", method = RequestMapping.Method.POST)
public class StudentRegisterController implements Command{

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp){
        StudentRepository studentRepository = (StudentRepository) req.getServletContext().getAttribute("studentRepository");
        String id = req.getParameter("id");
        String name = req.getParameter("name");
        Gender gender = Gender.valueOf(req.getParameter("gender"));
        int age = Integer.parseInt(req.getParameter("age"));

        if(Objects.isNull(id) || Objects.isNull(name) || age < 0 ){
            throw new RuntimeException("parameter is null or empty");
        }
        Student student = new Student(id, name, gender, age);
        log.error("id:{}", id);
        studentRepository.save(student);

        return "redirect:/student/list.do";
    }
}
