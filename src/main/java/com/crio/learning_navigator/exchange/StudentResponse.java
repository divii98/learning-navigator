package com.crio.learning_navigator.exchange;

import com.crio.learning_navigator.entity.Exam;
import com.crio.learning_navigator.entity.Subject;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentResponse {
    @JsonProperty("registrationId")
    private Long id;
    @JsonProperty("studentName")
    private String name;
    @JsonManagedReference
    @JsonProperty("enrolledSubjects")
    private List<SubjectResponse> subject;
    @JsonManagedReference
    @JsonProperty("registeredExams")
    private List<ExamResponse> exam;
}
