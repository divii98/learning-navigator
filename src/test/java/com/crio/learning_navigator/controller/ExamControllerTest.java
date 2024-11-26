package com.crio.learning_navigator.controller;

import com.crio.learning_navigator.entity.Subject;
import com.crio.learning_navigator.exchange.AddExamRequest;
import com.crio.learning_navigator.exchange.ExamResponse;
import com.crio.learning_navigator.exchange.SubjectResponse;
import com.crio.learning_navigator.service.ExamService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class ExamControllerTest {

    private final MockMvc mockMvc;

    @Mock
    private ExamService examService;

    @InjectMocks
    private ExamController examController;

    public ExamControllerTest() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(examController).build();
    }

    @Test
    void testAddExam() throws Exception {
        AddExamRequest addRequest = new AddExamRequest();
        SubjectResponse subject = new SubjectResponse();
        subject.setName("Math");
        ExamResponse response = new ExamResponse(1L,subject,new ArrayList<>());
        when(examService.addExam(any(AddExamRequest.class))).thenReturn(response);

        mockMvc.perform(post("/exam")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"Subject_ID\": \"1\" }"))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE));

        verify(examService, times(1)).addExam(any(AddExamRequest.class));
    }

    @Test
    void testGetExamByID() throws Exception {
        ExamResponse response = new ExamResponse();
        when(examService.getExam(1L)).thenReturn(response);

        mockMvc.perform(get("/exam/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE));

        verify(examService, times(1)).getExam(1L);
    }

    @Test
    void testGetAllExams() throws Exception {
        List<ExamResponse> responses = Arrays.asList(new ExamResponse(), new ExamResponse());
        when(examService.getAllExam()).thenReturn(responses);

        mockMvc.perform(get("/exam"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE));

        verify(examService, times(1)).getAllExam();
    }

    @Test
    void testDeleteExam() throws Exception {
        doNothing().when(examService).deleteExam(1L);

        mockMvc.perform(delete("/exam/1"))
                .andExpect(status().isNoContent());

        verify(examService, times(1)).deleteExam(1L);
    }
}
