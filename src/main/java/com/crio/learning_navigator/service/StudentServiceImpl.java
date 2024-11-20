package com.crio.learning_navigator.service;

import com.crio.learning_navigator.constant.Constant;
import com.crio.learning_navigator.entity.Student;
import com.crio.learning_navigator.entity.Subject;
import com.crio.learning_navigator.exception.NotExistException;
import com.crio.learning_navigator.exception.SubjectAlreadyPresentException;
import com.crio.learning_navigator.exchange.PostAddRequest;
import com.crio.learning_navigator.exchange.StudentResponse;
import com.crio.learning_navigator.exchange.UpdateRequest;
import com.crio.learning_navigator.respository.StudentRepository;
import com.crio.learning_navigator.respository.SubjectRepository;
import jakarta.persistence.EntityManager;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    SubjectRepository subjectRepository;
    @Autowired
    EntityManager entityManager;
    @Autowired
    StudentRepository studentRepository;
    ModelMapper mapper = new ModelMapper();

    @Override
    public StudentResponse addStudent(PostAddRequest addRequest) {
        Student student = new Student(addRequest.getName());
        return mapper.map(studentRepository.save(student), StudentResponse.class);
    }

    @Override
    public StudentResponse getStudent(Long id) {
        Student student = studentRepository.getReferenceById(id);
//                .orElseThrow(() -> new NotExistException(Constant.STUDENT_NOT_FOUND));
        return mapper.map(student, StudentResponse.class);
    }

    @Override
    public List<StudentResponse> getAllStudent() {
        return mapper.map(studentRepository.findAll(), new TypeToken<List<StudentResponse>>() {
        }.getType());
    }

    @Override
    public void deleteStudent(Long id) {
        if (!studentRepository.existsById(id))
            throw new NotExistException(Constant.STUDENT_NOT_FOUND);
        studentRepository.deleteById(id);
    }

    @Transactional
    @Override
    public StudentResponse updateSubject(UpdateRequest body, Long studentId) {
        Subject subject = subjectRepository.findById(body.getId())
                .orElseThrow(() -> new NotExistException(Constant.SUBJECT_NOT_FOUND));
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new NotExistException(Constant.STUDENT_NOT_FOUND));
        if(student.getEnrolledSubjects().contains(subject))
                throw new SubjectAlreadyPresentException(Constant.SUBJECT_ALREADY_PRESENT);
        student.getEnrolledSubjects().add(subject);
        StudentResponse studentResponse =  mapper.map(studentRepository.saveAndFlush(student), StudentResponse.class);
        entityManager.flush();
        return studentResponse;
    }
}
