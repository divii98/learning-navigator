package com.crio.learning_navigator.exchange;

import com.crio.learning_navigator.entity.Student;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
public class SubjectResponse {
    @JsonProperty("Subject Id")
    private Long subId;
    @JsonProperty("Subject Name")
    private String name;
    @JsonProperty("Registered Students")
    private Set<Student> students;
}
