package com.nhnacademy.student.servlet;

import com.nhnacademy.student.student.Student;
import com.nhnacademy.student.student.StudentRepository;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
@Deprecated
@Slf4j
@WebServlet(name = "studentListServlet", urlPatterns = "/student/list")
public class StudentListServlet extends HttpServlet {

    private StudentRepository studentRepository;

    @Override
    public void init(ServletConfig config) throws ServletException {
        studentRepository = (StudentRepository) config.getServletContext().getAttribute("studentRepository");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Student> studentList = studentRepository.getStudents();

        req.setAttribute("studentList", studentList);
//        RequestDispatcher rd = req.getRequestDispatcher("/student/list.jsp");
//        rd.forward(req, resp);

        req.setAttribute("view", "/student/list.jsp");
    }

}
