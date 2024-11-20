package com.crio.learning_navigator.controller;

import com.crio.learning_navigator.exchange.PostAddRequest;
import com.crio.learning_navigator.exchange.SubjectResponse;
import com.crio.learning_navigator.exchange.UpdateRequest;
import com.crio.learning_navigator.service.SubjectService;
import com.crio.learning_navigator.service.SubjectService;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@RestController
@RequestMapping("/subject")
public class SubjectController {
    @Autowired
    SubjectService subjectService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public SubjectResponse addSubject(@Valid @RequestBody PostAddRequest addRequest) {
        return subjectService.addSubject(addRequest);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public SubjectResponse getSubjectByID(@PathVariable Long id) {
        return subjectService.getSubject(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<SubjectResponse> getAllSubjects() {
        return subjectService.getAllSubject();
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}")
    public SubjectResponse updateSubject(@Valid @RequestBody UpdateRequest body,@PathVariable Long id) {
        return subjectService.updateSubject(body,id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteSubject(@PathVariable Long id) {
        subjectService.deleteSubject(id);
    }
}
