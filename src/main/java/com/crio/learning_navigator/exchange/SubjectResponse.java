package com.crio.learning_navigator.exchange;

import com.crio.learning_navigator.entity.Student;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
public class SubjectResponse {
    @JsonProperty("subjectId")
    private Long id;
    @JsonProperty("subjectName")
    private String name;
    @JsonBackReference
    @JsonProperty("registeredStudents")
    private List<StudentResponse> student;
}
