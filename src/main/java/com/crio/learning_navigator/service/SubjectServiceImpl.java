package com.crio.learning_navigator.service;

import com.crio.learning_navigator.constant.Constant;
import com.crio.learning_navigator.entity.Student;
import com.crio.learning_navigator.entity.Subject;
import com.crio.learning_navigator.exception.NotExistException;
import com.crio.learning_navigator.exchange.PostAddRequest;
import com.crio.learning_navigator.exchange.SubjectResponse;
import com.crio.learning_navigator.exchange.UpdateRequest;
import com.crio.learning_navigator.respository.StudentRepository;
import com.crio.learning_navigator.respository.SubjectRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectServiceImpl implements SubjectService {
    @Autowired
    SubjectRepository subjectRepository;

    @Autowired
    StudentRepository studentRepository;
    ModelMapper mapper = new ModelMapper();

    @Override
    public SubjectResponse addSubject(PostAddRequest addRequest) {
        Subject student = new Subject(addRequest.getName());
        return mapper.map(subjectRepository.save(student), SubjectResponse.class);
    }

    @Override
    public SubjectResponse getSubject(Long id) {
        Subject student = subjectRepository.findById(id)
                .orElseThrow(() -> new NotExistException(Constant.SUBJECT_NOT_FOUND));
        return mapper.map(student, SubjectResponse.class);
    }

    @Override
    public List<SubjectResponse> getAllSubject() {
        return mapper.map(subjectRepository.findAll(), new TypeToken<List<SubjectResponse>>() {
        }.getType());
    }

    @Override
    public void deleteSubject(Long id) {
        if (!subjectRepository.existsById(id))
            throw new NotExistException(Constant.SUBJECT_NOT_FOUND);
        subjectRepository.deleteById(id);
    }

    @Transactional
    @Override
    public SubjectResponse updateSubject(UpdateRequest updateRequest, Long subId) {
        Subject subject = subjectRepository.findById(subId)
                .orElseThrow(() -> new NotExistException(Constant.SUBJECT_NOT_FOUND));
        Student student = studentRepository.findById(updateRequest.getId())
                .orElseThrow(()->new NotExistException(Constant.STUDENT_NOT_FOUND));
        subject.getStudents().add(student);
        student.getEnrolledSubjects().add(subject);
        return mapper.map(subjectRepository.save(subject),SubjectResponse.class);
    }
}
