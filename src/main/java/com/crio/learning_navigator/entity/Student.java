package com.crio.learning_navigator.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.engine.internal.Cascade;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@Table(name = "student")
public class Student {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long regId;

    @Column
    private String name;

    @JsonManagedReference
    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinTable(
            name = "student_subject",
            joinColumns = @JoinColumn(name = "student_regId"),
            inverseJoinColumns = @JoinColumn(name = "subject_subId")
    )
    private Set<Subject> enrolledSubjects;

    @JsonManagedReference
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "student_exam",
            joinColumns = @JoinColumn(name = "student_regId"),
            inverseJoinColumns = @JoinColumn(name = "exam_examId")
    )
    private Set<Exam> registeredExams;

    public Student(String name) {
        this.name = name;
    }
}
