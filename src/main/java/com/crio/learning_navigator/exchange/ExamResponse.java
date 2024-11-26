package com.crio.learning_navigator.exchange;

import com.crio.learning_navigator.entity.Student;
import com.crio.learning_navigator.entity.Subject;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExamResponse {
    @JsonProperty("examId")
    private Long id;

    @JsonManagedReference
    @JsonProperty("subject")
    private SubjectResponse subject;

    @JsonBackReference
    @JsonProperty("registeredStudents")
    private List<StudentResponse> student;
}
