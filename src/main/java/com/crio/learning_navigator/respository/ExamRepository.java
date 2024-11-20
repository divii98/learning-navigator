package com.crio.learning_navigator.respository;

import com.crio.learning_navigator.entity.Exam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
public interface ExamRepository extends JpaRepository<Exam,Long> {
}
