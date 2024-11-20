package com.crio.learning_navigator.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@Table(name = "exam")
public class Exam {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long examId;

    @Column
    private String name;

    @JsonBackReference
    @ManyToMany(mappedBy = "registeredExams")
    private Set<Student> students;

    public Exam(String name) {
        this.name = name;
        this.students = new HashSet<>();
    }
}
