package com.nhnacademy.student.servlet;

import com.nhnacademy.student.student.Student;
import com.nhnacademy.student.student.StudentRepository;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Deprecated
@WebServlet(name="studentViewServlet", urlPatterns = "/student/view")
public class StudentViewServlet extends HttpServlet {
    private StudentRepository studentRepository;

    @Override
    public void init() throws ServletException {
        studentRepository = (StudentRepository) getServletContext().getAttribute("studentRepository");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        if(id == null){
            throw new RuntimeException("id is null");
        }

        Student student = studentRepository.getStudentById(id);
        req.setAttribute("student",student);

//        RequestDispatcher rd = req.getRequestDispatcher("/student/view.jsp");
//        rd.forward(req, resp);
        req.setAttribute("view" , "/student/view.jsp");
    }


}
