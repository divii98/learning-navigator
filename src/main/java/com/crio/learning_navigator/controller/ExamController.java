package com.crio.learning_navigator.controller;

import com.crio.learning_navigator.exchange.ExamResponse;
import com.crio.learning_navigator.exchange.PostAddRequest;
import com.crio.learning_navigator.service.ExamService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("exam")
public class ExamController {
    @Autowired
    ExamService examService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ExamResponse addExam(@Valid @RequestBody PostAddRequest addRequest) {
        return examService.addExam(addRequest);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public ExamResponse getExamByID(@PathVariable Long id) {
        return examService.getExam(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<ExamResponse> getAllExams() {
        return examService.getAllExam();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteExam(@PathVariable Long id) {
        examService.deleteExam(id);
    }

}
