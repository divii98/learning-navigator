package com.crio.learning_navigator.controller;

import com.crio.learning_navigator.exchange.PostAddRequest;
import com.crio.learning_navigator.exchange.StudentResponse;
import com.crio.learning_navigator.exchange.UpdateRequest;
import com.crio.learning_navigator.service.StudentService;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    StudentService studentService;
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public StudentResponse addStudent(@Valid @RequestBody PostAddRequest addRequest) {
        return studentService.addStudent(addRequest);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public StudentResponse getStudentByID(@PathVariable Long id) {
        return studentService.getStudent(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<StudentResponse> getAllStudents() {
        return studentService.getAllStudent();
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{studentId}/addSubject")
    public StudentResponse updateSubject(@Valid @RequestBody UpdateRequest body, @PathVariable Long studentId) {
        return studentService.updateSubject(body,studentId);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{studentId}/addExam")
    public StudentResponse updateExam(@Valid @RequestBody UpdateRequest body, @PathVariable Long studentId) {
        return studentService.updateExam(body,studentId);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
    }
}
