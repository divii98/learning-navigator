package com.crio.learning_navigator.service;

import com.crio.learning_navigator.constant.Constant;
import com.crio.learning_navigator.entity.Exam;
import com.crio.learning_navigator.entity.Subject;
import com.crio.learning_navigator.exception.NotExistException;
import com.crio.learning_navigator.exchange.AddExamRequest;
import com.crio.learning_navigator.exchange.ExamResponse;
import com.crio.learning_navigator.exchange.SubjectResponse;
import com.crio.learning_navigator.respository.ExamRepository;
import com.crio.learning_navigator.respository.SubjectRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ExamServiceImplTest {

    @Mock
    private ExamRepository examRepository;

    @Mock
    private SubjectRepository subjectRepository;

    @Mock
    private ModelMapper mapper;

    @InjectMocks
    private ExamServiceImpl examService;



    @Test
    void addExam_SubjectNotFound() {
        AddExamRequest addExamRequest = new AddExamRequest();
        addExamRequest.setId(1L);

        when(subjectRepository.findById(1L)).thenReturn(Optional.empty());

        NotExistException exception = assertThrows(NotExistException.class,
                () -> examService.addExam(addExamRequest));

        assertEquals(Constant.SUBJECT_NOT_FOUND, exception.getMessage());
        verify(subjectRepository, times(1)).findById(1L);
        verify(examRepository, never()).save(any());
    }



    @Test
    void getExam_NotFound() {
        when(examRepository.findById(1L)).thenReturn(Optional.empty());

        NotExistException exception = assertThrows(NotExistException.class,
                () -> examService.getExam(1L));

        assertEquals(Constant.EXAM_NOT_FOUND, exception.getMessage());
        verify(examRepository, times(1)).findById(1L);
    }

    @Test
    void getAllExam_Success() {
        Subject subject = new Subject();
        subject.setId(1L);
        subject.setName("Math");
        Exam exam = new Exam(subject);
        ExamResponse examRes = new ExamResponse();
        examRes.setId(1L);
        examRes.setSubject(mapper.map(subject, SubjectResponse.class));

        List<Exam> exams = Collections.singletonList(exam);
        List<ExamResponse> responses = Collections.singletonList(examRes);

        when(examRepository.findAll()).thenReturn(exams);
        when(mapper.map(exams, new TypeToken<List<ExamResponse>>() {}.getType())).thenReturn(responses);

        List<ExamResponse> result = examService.getAllExam();

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(examRepository, times(1)).findAll();
    }

    @Test
    void deleteExam_Success() {
        when(examRepository.existsById(1L)).thenReturn(true);

        examService.deleteExam(1L);

        verify(examRepository, times(1)).deleteById(1L);
    }

    @Test
    void deleteExam_NotFound() {
        when(examRepository.existsById(1L)).thenReturn(false);

        NotExistException exception = assertThrows(NotExistException.class,
                () -> examService.deleteExam(1L));

        assertEquals(Constant.EXAM_NOT_FOUND, exception.getMessage());
        verify(examRepository, never()).deleteById(1L);
    }
}
