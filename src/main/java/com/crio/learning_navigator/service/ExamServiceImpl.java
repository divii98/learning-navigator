package com.crio.learning_navigator.service;

import com.crio.learning_navigator.constant.Constant;
import com.crio.learning_navigator.entity.Exam;
import com.crio.learning_navigator.entity.Subject;
import com.crio.learning_navigator.exception.NotExistException;
import com.crio.learning_navigator.exchange.AddExamRequest;
import com.crio.learning_navigator.exchange.ExamResponse;
import com.crio.learning_navigator.respository.ExamRepository;
import com.crio.learning_navigator.respository.SubjectRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExamServiceImpl implements ExamService {
    @Autowired
    ExamRepository examRepository;
    @Autowired
    SubjectRepository subjectRepository;
    ModelMapper mapper = new ModelMapper();

    @Override
    public ExamResponse addExam(AddExamRequest addRequest) {
        Subject subject = subjectRepository.findById(addRequest.getId())
                .orElseThrow(() -> new NotExistException(Constant.SUBJECT_NOT_FOUND));
        System.out.println("----------->"+subject);
        Exam exam = examRepository.save(new Exam(subject));
        System.out.println("---------->"+exam);
        ExamResponse examResponse = mapper.map(exam, ExamResponse.class);
        System.out.println("---------->"+examResponse);
        return examResponse;
    }

    @Override
    public ExamResponse getExam(Long id) {
        Exam exam = examRepository.findById(id)
                .orElseThrow(() -> new NotExistException(Constant.EXAM_NOT_FOUND));
        return mapper.map(exam, ExamResponse.class);
    }

    @Override
    public List<ExamResponse> getAllExam() {
        return mapper.map(examRepository.findAll(), new TypeToken<List<ExamResponse>>() {
        }.getType());
    }

    @Override
    public void deleteExam(Long id) {
        if (!examRepository.existsById(id))
            throw new NotExistException(Constant.EXAM_NOT_FOUND);
        examRepository.deleteById(id);
    }
}
