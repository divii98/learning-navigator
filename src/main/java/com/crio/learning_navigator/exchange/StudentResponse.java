package com.crio.learning_navigator.exchange;

import com.crio.learning_navigator.entity.Exam;
import com.crio.learning_navigator.entity.Subject;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
public class StudentResponse {
    @JsonProperty("Registration Id")
    private Long regId;
    @JsonProperty("Student Name")
    private String name;
    @JsonProperty("Enrolled Subjects")
    private Set<Subject> enrolledSubjects;
    @JsonProperty("Registered Exams")
    private Set<Exam> registeredExams;
}
