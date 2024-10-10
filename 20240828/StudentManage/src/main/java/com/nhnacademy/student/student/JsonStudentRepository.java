package com.nhnacademy.student.student;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

@Slf4j
public class JsonStudentRepository implements StudentRepository {
    private final ObjectMapper objectMapper;

    private static final String JSON_FILE_PATH = "D:/소프트웨어 아카데미/9주차/20240828/StudentManage/src/main/json/student.json";

    public JsonStudentRepository() {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        File jsonFile = new File(JSON_FILE_PATH);

        if(jsonFile.exists()) {
            try {
                Files.delete(Paths.get(JSON_FILE_PATH));
                log.error("json file deleted");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private synchronized List<Student> readJsonFile(){
        File file = new File(JSON_FILE_PATH);
        
        if (!file.exists()) {
            return new LinkedList<>();
        }
        List<Student> students;
        try(FileInputStream fileInputStream = new FileInputStream(file);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, StandardCharsets.UTF_8);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        ) {
            students = objectMapper.readValue(bufferedReader, new TypeReference<List<Student>>() {});
            return students;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        } catch(IOException e){
            throw new RuntimeException(e);
        }
    }

    private synchronized void writeJsonFile(List<Student> studentList){
        File file = new File(JSON_FILE_PATH);

        try(FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);)
        {
            objectMapper.writeValue(bufferedWriter, studentList);
        }catch(IOException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void save(Student student) {
        List<Student> studentList = readJsonFile();
        studentList.add(student);
        writeJsonFile(studentList);
    }

    @Override
    public void update(Student student) {
        List<Student> studentList = readJsonFile();

        for(int i = 0; i < studentList.size(); i++){
            if(studentList.get(i).getId().equals(student.getId())){
                studentList.set(i, student);
            }
        }
        writeJsonFile(studentList);
    }

    @Override
    public void deleteById(String id) {
        List<Student> studentList = readJsonFile();
        for(int i = 0; i < studentList.size(); i++){
            if(studentList.get(i).getId().equals(id)){
                studentList.remove(i);
                writeJsonFile(studentList);
                break;
            }
        }
    }

    @Override
    public Student getStudentById(String id) {
        List<Student> studentList = readJsonFile();
        for(int i = 0; i < studentList.size(); i++){
            if(studentList.get(i).getId().equals(id)){
                return studentList.get(i);
            }
        }
        return null;
    }

    @Override
    public List<Student> getStudents() {
        return readJsonFile();
    }

    @Override
    public boolean existById(String id) {
        List<Student> studentList = readJsonFile();
        return studentList.contains(getStudentById(id));
    }
}
