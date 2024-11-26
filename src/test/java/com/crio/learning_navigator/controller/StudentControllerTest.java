package com.crio.learning_navigator.controller;

import com.crio.learning_navigator.exchange.PostAddRequest;
import com.crio.learning_navigator.exchange.StudentResponse;
import com.crio.learning_navigator.exchange.UpdateRequest;
import com.crio.learning_navigator.service.StudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(StudentController.class)
public class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentService studentService;

    private StudentResponse studentResponse;

    @BeforeEach
    public void setup() {
        studentResponse = new StudentResponse(1L, "John Doe", Collections.emptyList(), Collections.emptyList());

    }

    @Test
    public void addStudent_ShouldReturnCreatedStatus() throws Exception {
        when(studentService.addStudent(any(PostAddRequest.class))).thenReturn(studentResponse);

        mockMvc.perform(post("/student")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"Name\":\"John Doe\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.registrationId").value(1))
                .andExpect(jsonPath("$.studentName").value("John Doe"));

        verify(studentService, times(1)).addStudent(any(PostAddRequest.class));
    }

    @Test
    public void getStudentByID_ShouldReturnStudent() throws Exception {
        when(studentService.getStudent(1L)).thenReturn(studentResponse);

        mockMvc.perform(get("/student/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.registrationId").value(1))
                .andExpect(jsonPath("$.studentName").value("John Doe"));

        verify(studentService, times(1)).getStudent(1L);
    }

    @Test
    public void getAllStudents_ShouldReturnListOfStudents() throws Exception {
        when(studentService.getAllStudent()).thenReturn(Collections.singletonList(studentResponse));

        mockMvc.perform(get("/student"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].registrationId").value(1))
                .andExpect(jsonPath("$[0].studentName").value("John Doe"));

        verify(studentService, times(1)).getAllStudent();
    }

    @Test
    public void updateSubject_ShouldReturnUpdatedStudent() throws Exception {
        when(studentService.updateSubject(any(UpdateRequest.class), eq(1L))).thenReturn(studentResponse);

        mockMvc.perform(put("/student/1/addSubject")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"Id\":1}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.registrationId").value(1))
                .andExpect(jsonPath("$.studentName").value("John Doe"));

        verify(studentService, times(1)).updateSubject(any(UpdateRequest.class), eq(1L));
    }

    @Test
    public void updateExam_ShouldReturnUpdatedStudent() throws Exception {
        when(studentService.updateExam(any(UpdateRequest.class), eq(1L))).thenReturn(studentResponse);

        mockMvc.perform(put("/student/1/addExam")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"Id\":1}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.registrationId").value(1))
                .andExpect(jsonPath("$.studentName").value("John Doe"));

        verify(studentService, times(1)).updateExam(any(UpdateRequest.class), eq(1L));
    }

    @Test
    public void deleteStudent_ShouldReturnNoContentStatus() throws Exception {
        doNothing().when(studentService).deleteStudent(1L);

        mockMvc.perform(delete("/student/1"))
                .andExpect(status().isNoContent());

        verify(studentService, times(1)).deleteStudent(1L);
    }
}
