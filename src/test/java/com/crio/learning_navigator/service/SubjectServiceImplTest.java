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
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
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
class SubjectServiceImplTest {

    @Mock
    private SubjectRepository subjectRepository;

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private ModelMapper mapper;

    @InjectMocks
    private SubjectServiceImpl subjectService;

    @Test
    void addSubject_Success() {
        PostAddRequest addRequest = new PostAddRequest("Math");
        Subject subject = new Subject("Math");
        SubjectResponse subjectResponse = new SubjectResponse();
        subjectResponse.setId(1L);
        subjectResponse.setName("Math");

        when(subjectRepository.save(any(Subject.class))).thenReturn(subject);
        when(mapper.map(subject, SubjectResponse.class)).thenReturn(subjectResponse);

        SubjectResponse response = subjectService.addSubject(addRequest);

        assertNotNull(response);
        assertEquals("Math", response.getName());
        verify(subjectRepository, times(1)).save(any(Subject.class));
    }

    @Test
    void getSubject_Success() {
        Subject subject = new Subject("Math");
        subject.setId(1L);
        SubjectResponse subjectResponse = new SubjectResponse();
        subjectResponse.setId(1L);
        subjectResponse.setName("Math");

        when(subjectRepository.findById(1L)).thenReturn(Optional.of(subject));
        when(mapper.map(subject, SubjectResponse.class)).thenReturn(subjectResponse);

        SubjectResponse response = subjectService.getSubject(1L);

        assertNotNull(response);
        assertEquals("Math", response.getName());
        verify(subjectRepository, times(1)).findById(1L);
    }

    @Test
    void getSubject_NotFound() {
        when(subjectRepository.findById(1L)).thenReturn(Optional.empty());

        NotExistException exception = assertThrows(NotExistException.class,
                () -> subjectService.getSubject(1L));

        assertEquals(Constant.SUBJECT_NOT_FOUND, exception.getMessage());
        verify(subjectRepository, times(1)).findById(1L);
    }

    @Test
    void getAllSubjects_Success() {
        Subject subject = new Subject("Math");
        subject.setId(1L);
        List<Subject> subjects = Collections.singletonList(subject);
        SubjectResponse subjectResponse = new SubjectResponse();
        subjectResponse.setId(1L);
        subjectResponse.setName("Math");
        List<SubjectResponse> responses = Collections.singletonList(subjectResponse);

        when(subjectRepository.findAll()).thenReturn(subjects);
        when(mapper.map(subjects, new TypeToken<List<SubjectResponse>>() {}.getType())).thenReturn(responses);

        List<SubjectResponse> result = subjectService.getAllSubject();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Math", result.get(0).getName());
        verify(subjectRepository, times(1)).findAll();
    }

    @Test
    void deleteSubject_Success() {
        when(subjectRepository.existsById(1L)).thenReturn(true);

        subjectService.deleteSubject(1L);

        verify(subjectRepository, times(1)).deleteById(1L);
    }

    @Test
    void deleteSubject_NotFound() {
        when(subjectRepository.existsById(1L)).thenReturn(false);

        NotExistException exception = assertThrows(NotExistException.class,
                () -> subjectService.deleteSubject(1L));

        assertEquals(Constant.SUBJECT_NOT_FOUND, exception.getMessage());
        verify(subjectRepository, never()).deleteById(1L);
    }

    @Test
    void updateSubject_Success() {
        Subject subject = new Subject("Math");
        subject.setId(1L);
        subject.setStudent(new ArrayList<>());

        Student student = new Student();
        student.setId(2L);
        student.setName("John");
        student.setSubject(new ArrayList<>());

        UpdateRequest updateRequest = new UpdateRequest(2L);
        SubjectResponse subjectResponse = new SubjectResponse();
        subjectResponse.setId(1L);
        subjectResponse.setName("Math");

        when(subjectRepository.findById(1L)).thenReturn(Optional.of(subject));
        when(studentRepository.findById(2L)).thenReturn(Optional.of(student));
        when(subjectRepository.save(any(Subject.class))).thenReturn(subject);
        when(mapper.map(subject, SubjectResponse.class)).thenReturn(subjectResponse);

        SubjectResponse response = subjectService.updateSubject(updateRequest, 1L);

        assertNotNull(response);
        assertEquals("Math", response.getName());
        assertTrue(subject.getStudent().contains(student));
        assertTrue(student.getSubject().contains(subject));
        verify(subjectRepository, times(1)).findById(1L);
        verify(studentRepository, times(1)).findById(2L);
        verify(subjectRepository, times(1)).save(subject);
    }

    @Test
    void updateSubject_SubjectNotFound() {
        UpdateRequest updateRequest = new UpdateRequest(2L);

        when(subjectRepository.findById(1L)).thenReturn(Optional.empty());

        NotExistException exception = assertThrows(NotExistException.class,
                () -> subjectService.updateSubject(updateRequest, 1L));

        assertEquals(Constant.SUBJECT_NOT_FOUND, exception.getMessage());
        verify(subjectRepository, times(1)).findById(1L);
        verify(studentRepository, never()).findById(anyLong());
        verify(subjectRepository, never()).save(any());
    }

    @Test
    void updateSubject_StudentNotFound() {
        Subject subject = new Subject("Math");
        subject.setId(1L);

        UpdateRequest updateRequest = new UpdateRequest(2L);

        when(subjectRepository.findById(1L)).thenReturn(Optional.of(subject));
        when(studentRepository.findById(2L)).thenReturn(Optional.empty());

        NotExistException exception = assertThrows(NotExistException.class,
                () -> subjectService.updateSubject(updateRequest, 1L));

        assertEquals(Constant.STUDENT_NOT_FOUND, exception.getMessage());
        verify(subjectRepository, times(1)).findById(1L);
        verify(studentRepository, times(1)).findById(2L);
        verify(subjectRepository, never()).save(any());
    }
}
