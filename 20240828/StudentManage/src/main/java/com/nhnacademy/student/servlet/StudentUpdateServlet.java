package com.nhnacademy.student.servlet;

import com.nhnacademy.student.student.Gender;
import com.nhnacademy.student.student.Student;
import com.nhnacademy.student.student.StudentRepository;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Deprecated
@WebServlet(name="studentUpdateServlet", urlPatterns = "/student/update")
public class StudentUpdateServlet extends HttpServlet {
    private StudentRepository studentRepository;

    @Override
    public void init(ServletConfig config) throws ServletException {
        studentRepository = (StudentRepository) config.getServletContext().getAttribute("studentRepository");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String id = req.getParameter("id");

        if(id == null){
            throw new ServletException("id is null");
        }

        Student student = studentRepository.getStudentById(id);

        req.setAttribute("student", student);

//        RequestDispatcher rd = req.getRequestDispatcher("/student/register.jsp");
//        rd.forward(req, resp);
        req.setAttribute("view", "/student/register.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String id = req.getParameter("id");
        String name = req.getParameter("name");
        Gender gender = Gender.valueOf(req.getParameter("gender"));
        int age = Integer.parseInt(req.getParameter("age"));

        if(id == null || name == null || age < 0){
            throw new ServletException("parameter is null or less than 0");
        }

        Student student = new Student(id, name, gender, age);

        studentRepository.update(student);

//        resp.sendRedirect(req.getContextPath() + "/student/list");
        req.setAttribute("view", "redirect:/student/list.do");
    }
}
