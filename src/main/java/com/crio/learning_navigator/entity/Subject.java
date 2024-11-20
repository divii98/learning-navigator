package com.crio.learning_navigator.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@Table(name = "subject")
public class Subject {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long subId;

    @Column
    private String name;

    @JsonBackReference
    @ManyToMany(mappedBy = "enrolledSubjects",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Set<Student> students;

    public Subject(String name) {
        this.name = name;
        this.students = new HashSet<>();
    }
}
