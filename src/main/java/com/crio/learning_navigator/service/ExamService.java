package com.crio.learning_navigator.service;

import com.crio.learning_navigator.exchange.AddExamRequest;
import com.crio.learning_navigator.exchange.PostAddRequest;
import com.crio.learning_navigator.exchange.ExamResponse;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ExamService {
    ExamResponse addExam(AddExamRequest addRequest);
    ExamResponse getExam(Long id);
    List<ExamResponse> getAllExam();
    void deleteExam(Long id);
}
