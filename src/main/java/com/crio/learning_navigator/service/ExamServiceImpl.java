package com.crio.learning_navigator.service;

import com.crio.learning_navigator.constant.Constant;
import com.crio.learning_navigator.entity.Exam;
import com.crio.learning_navigator.exception.NotExistException;
import com.crio.learning_navigator.exchange.PostAddRequest;
import com.crio.learning_navigator.exchange.ExamResponse;
import com.crio.learning_navigator.respository.ExamRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ExamServiceImpl implements ExamService{
    @Autowired
    ExamRepository examRepository;
    ModelMapper mapper = new ModelMapper();

    @Override
    public ExamResponse addExam(PostAddRequest addRequest) {
        Exam student = new Exam(addRequest.getName());
        return mapper.map(examRepository.save(student),ExamResponse.class);
    }

    @Override
    public ExamResponse getExam(Long id) {
        Exam student = examRepository.findById(id)
                .orElseThrow(()->new NotExistException(Constant.EXAM_NOT_FOUND));
        return mapper.map(student,ExamResponse.class);
    }

    @Override
    public List<ExamResponse> getAllExam() {
        return mapper.map(examRepository.findAll(), new TypeToken<List<ExamResponse>>(){}.getType());
    }

    @Override
    public void deleteExam(Long id) {
        if(!examRepository.existsById(id))
            throw new NotExistException(Constant.EXAM_NOT_FOUND);
        examRepository.deleteById(id);
    }
}
