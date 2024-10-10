package com.nhnacademy.jdbc.student.domain;

import java.time.LocalDateTime;

public class Student {

    public enum GENDER{
        M,F
    }
    private String id;
    private String name;
    private GENDER gender;
    private Integer age;
    private LocalDateTime createdAt;

    //todo#0 필요한 method가 있다면 추가합니다.

    public Student(){

    }
    public Student(String id, String name, GENDER gender, Integer age){
        this.name = name;
        this.id = id;
        this.gender = gender;
        this.age = age;
        this.createdAt = LocalDateTime.now();
    }

    public Student(String id, String name, GENDER gender, Integer age, LocalDateTime createdAt){
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.createdAt = createdAt;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public GENDER getGender() {
        return gender;
    }

    public Integer getAge() {
        return age;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    @Override
    public String toString(){
        return "{id: " + id + ", name: " + name + ", age: " + age + ", createdAt: " + createdAt + "}";
    }
}
