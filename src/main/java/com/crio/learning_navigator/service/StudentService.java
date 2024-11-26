package com.crio.learning_navigator.service;

import com.crio.learning_navigator.exchange.PostAddRequest;
import com.crio.learning_navigator.exchange.StudentResponse;
import com.crio.learning_navigator.exchange.UpdateRequest;

import java.util.List;

public interface StudentService {
    StudentResponse addStudent(PostAddRequest addRequest);
    StudentResponse getStudent(Long id);
    List<StudentResponse> getAllStudent();
    void deleteStudent(Long id);
    StudentResponse updateSubject(UpdateRequest body, Long studentId);
    StudentResponse updateExam(UpdateRequest body, Long studentId);
}
