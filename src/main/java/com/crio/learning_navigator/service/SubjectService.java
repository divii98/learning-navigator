package com.crio.learning_navigator.service;

import com.crio.learning_navigator.exchange.PostAddRequest;
import com.crio.learning_navigator.exchange.SubjectResponse;
import com.crio.learning_navigator.exchange.UpdateRequest;

import java.util.List;

public interface SubjectService {
    SubjectResponse addSubject(PostAddRequest addRequest);

    SubjectResponse getSubject(Long id);

    List<SubjectResponse> getAllSubject();

    void deleteSubject(Long id);

    SubjectResponse updateSubject(UpdateRequest updateRequest, Long subId);
}
