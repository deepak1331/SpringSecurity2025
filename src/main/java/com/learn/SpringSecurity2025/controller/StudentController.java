package com.learn.SpringSecurity2025.controller;

import com.learn.SpringSecurity2025.model.Student;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class StudentController {

    private static final Logger log = LogManager.getLogger(StudentController.class);
    List<Student> studentList = new ArrayList<>();
    {
        studentList.add(new Student(1, "Deepak Yadav", 86));
        studentList.add(new Student(2, "Shipra Yadav", 96));
        studentList.add(new Student(3, "Avyaan Yadav", 100));
    }

    @GetMapping("/students")
    public ResponseEntity<List<Student>> getStudents() {
        log.info("Total no of students available currently: {}", studentList.size());
        return ResponseEntity.ok(studentList);
    }


    @PostMapping("/students")
    public ResponseEntity<Student> addStudent(@RequestBody Student student) {
        log.info("Adding Student: {}", student);
        try {
            studentList.add(student);
            log.info("Student Added Successfully! New List Size : {}", studentList.size());
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
        }
        return ResponseEntity.ok(student);
    }
}