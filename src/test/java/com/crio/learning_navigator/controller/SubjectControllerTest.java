package com.crio.learning_navigator.controller;

import com.crio.learning_navigator.exchange.PostAddRequest;
import com.crio.learning_navigator.exchange.SubjectResponse;
import com.crio.learning_navigator.exchange.UpdateRequest;
import com.crio.learning_navigator.service.SubjectService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(SubjectController.class)
class SubjectControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SubjectService subjectService;


    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void addSubject_Success() throws Exception {
        PostAddRequest addRequest = new PostAddRequest("Math");
        SubjectResponse subjectResponse = new SubjectResponse();
        subjectResponse.setId(1L);
        subjectResponse.setName("Math");

        when(subjectService.addSubject(any(PostAddRequest.class))).thenReturn(subjectResponse);

        mockMvc.perform(post("/subject")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(addRequest)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.subjectId").value(1L))
                .andExpect(jsonPath("$.subjectName").value("Math"));
    }

    @Test
    void getSubjectByID_Success() throws Exception {
        SubjectResponse subjectResponse = new SubjectResponse();
        subjectResponse.setId(1L);
        subjectResponse.setName("Math");

        when(subjectService.getSubject(1L)).thenReturn(subjectResponse);

        mockMvc.perform(get("/subject/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.subjectId").value(1L))
                .andExpect(jsonPath("$.subjectName").value("Math"));
    }

    @Test
    void getAllSubjects_Success() throws Exception {
        SubjectResponse subjectResponse = new SubjectResponse();
        subjectResponse.setId(1L);
        subjectResponse.setName("Math");
        List<SubjectResponse> responses = Collections.singletonList(subjectResponse);

        when(subjectService.getAllSubject()).thenReturn(responses);

        mockMvc.perform(get("/subject"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].subjectId").value(1L))
                .andExpect(jsonPath("$[0].subjectName").value("Math"));
    }

    @Test
    void updateSubject_Success() throws Exception {
        UpdateRequest updateRequest = new UpdateRequest();
        updateRequest.setId(1L);
        SubjectResponse subjectResponse = new SubjectResponse();
        subjectResponse.setId(1L);
        subjectResponse.setName("Advanced Math");

        when(subjectService.updateSubject(any(UpdateRequest.class), eq(1L))).thenReturn(subjectResponse);

        mockMvc.perform(put("/subject/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateRequest)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.subjectId").value(1L))
                .andExpect(jsonPath("$.subjectName").value("Advanced Math"));
    }

    @Test
    void deleteSubject_Success() throws Exception {
        Mockito.doNothing().when(subjectService).deleteSubject(1L);

        mockMvc.perform(delete("/subject/{id}", 1L))
                .andExpect(status().isNoContent());
    }
}
