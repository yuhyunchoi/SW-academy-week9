package com.nhnacademy.student.student;

import java.util.List;

public interface StudentRepository {
    //학생 -등록
    void save(Student student);
    //학생 - 수정
    void update(Student student);
    //학생 - 삭제
    void deleteById(String id);
    //학생_조회
    Student getStudentById(String id);
    //학생 - 아이디 존재 여부
    List<Student> getStudents();
    //g학생 - 아이디 존재 여부
    boolean existById(String id);
}
