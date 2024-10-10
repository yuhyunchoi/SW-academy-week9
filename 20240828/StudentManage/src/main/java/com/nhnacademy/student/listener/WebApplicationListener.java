package com.nhnacademy.student.listener;

import com.nhnacademy.student.student.JsonStudentRepository;
import com.nhnacademy.student.student.Gender;
import com.nhnacademy.student.student.Student;
import com.nhnacademy.student.student.StudentRepository;
import org.apache.commons.math3.random.RandomDataGenerator;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.time.LocalDateTime;

@WebListener
public class WebApplicationListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        StudentRepository studentRepository = new JsonStudentRepository();
        RandomDataGenerator randomDataGenerator = new RandomDataGenerator();
        for(int i = 1 ; i <= 10 ;i++){
            Student student = new Student();
            student.setId("student" + i);
            student.setName("아카데미"+ i);
            student.setGender(Gender.randomGender());
            student.setAge(randomDataGenerator.nextInt(20,30));
            student.setCreatedAt(LocalDateTime.now());
            studentRepository.save(student);
        }
        context.setAttribute("studentRepository", studentRepository);

    }
}
