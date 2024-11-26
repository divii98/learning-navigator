package com.crio.learning_navigator.service;

import com.crio.learning_navigator.constant.Constant;
import com.crio.learning_navigator.entity.Exam;
import com.crio.learning_navigator.entity.Student;
import com.crio.learning_navigator.entity.Subject;
import com.crio.learning_navigator.exception.ExamAlreadyPresentException;
import com.crio.learning_navigator.exception.NotExistException;
import com.crio.learning_navigator.exception.SubjectAlreadyPresentException;
import com.crio.learning_navigator.exchange.PostAddRequest;
import com.crio.learning_navigator.exchange.StudentResponse;
import com.crio.learning_navigator.exchange.UpdateRequest;
import com.crio.learning_navigator.respository.ExamRepository;
import com.crio.learning_navigator.respository.StudentRepository;
import com.crio.learning_navigator.respository.SubjectRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StudentServiceImplTest {

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private SubjectRepository subjectRepository;

    @Mock
    private ExamRepository examRepository;

    @InjectMocks
    private StudentServiceImpl studentService;

    private ModelMapper mapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mapper = new ModelMapper();
        studentService.mapper = mapper;
    }

    @Test
    void addStudent_ShouldAddAndReturnStudent() {
        PostAddRequest request = new PostAddRequest("John Doe");
        Student student = new Student("John Doe");
        student.setId(1L);

        when(studentRepository.save(any(Student.class))).thenReturn(student);

        StudentResponse response = studentService.addStudent(request);

        assertNotNull(response);
        assertEquals("John Doe", response.getName());
        verify(studentRepository, times(1)).save(any(Student.class));
    }

    @Test
    void getStudent_ShouldReturnStudent() {
        Student student = new Student("John Doe");
        student.setId(1L);

        when(studentRepository.getReferenceById(1L)).thenReturn(student);

        StudentResponse response = studentService.getStudent(1L);

        assertNotNull(response);
        assertEquals(1L, response.getId());
        verify(studentRepository, times(1)).getReferenceById(1L);
    }

    @Test
    void getStudent_ShouldThrowNotExistException() {
        when(studentRepository.getReferenceById(1L)).thenThrow(new NotExistException(Constant.STUDENT_NOT_FOUND));

        NotExistException exception = assertThrows(NotExistException.class, () -> studentService.getStudent(1L));

        assertEquals(Constant.STUDENT_NOT_FOUND, exception.getMessage());
    }

    @Test
    void deleteStudent_ShouldDeleteStudent() {
        when(studentRepository.existsById(1L)).thenReturn(true);

        studentService.deleteStudent(1L);

        verify(studentRepository, times(1)).deleteById(1L);
    }

    @Test
    void deleteStudent_ShouldThrowNotExistException() {
        when(studentRepository.existsById(1L)).thenReturn(false);

        NotExistException exception = assertThrows(NotExistException.class, () -> studentService.deleteStudent(1L));

        assertEquals(Constant.STUDENT_NOT_FOUND, exception.getMessage());
    }

    @Test
    void updateSubject_ShouldAddSubjectToStudent() {
        Subject subject = new Subject("Math");
        subject.setId(1L);
        Student student = new Student("John Doe");
        student.setId(1L);
        Student responseStudent = new Student("John Doe");
        responseStudent.setId(1L);
        responseStudent.getSubject().add(subject);

        when(subjectRepository.findById(1L)).thenReturn(Optional.of(subject));
        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));
        when(studentRepository.saveAndFlush(any(Student.class))).thenReturn(responseStudent);

        StudentResponse response = studentService.updateSubject(new UpdateRequest(1L), 1L);
        assertNotNull(response);
        assertTrue(student.getSubject().contains(subject));
        verify(studentRepository, times(1)).saveAndFlush(student);
    }

    @Test
    void updateSubject_ShouldThrowSubjectAlreadyPresentException() {
        Subject subject = new Subject("Math");
        subject.setId(1L);
        Student student = new Student("John Doe");
        student.setId(1L);
        student.getSubject().add(subject);

        when(subjectRepository.findById(1L)).thenReturn(Optional.of(subject));
        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));

        SubjectAlreadyPresentException exception = assertThrows(
                SubjectAlreadyPresentException.class,
                () -> studentService.updateSubject(new UpdateRequest(1L), 1L)
        );

        assertEquals(Constant.SUBJECT_ALREADY_PRESENT, exception.getMessage());
    }

    @Test
    void updateExam_ShouldAddExamToStudent() {
        Subject subject = new Subject("Math");
        Exam exam = new Exam(subject);
        exam.setId(1L);

        Student student = new Student("John Doe");
        Student studentReturn = new Student("John Doe");
        studentReturn.getSubject().add(subject);
        studentReturn.getExam().add(exam);
        student.setId(1L);
        student.getSubject().add(subject);

        when(examRepository.findById(1L)).thenReturn(Optional.of(exam));
        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));
        when(studentRepository.save(any(Student.class))).thenReturn(studentReturn);

        StudentResponse response = studentService.updateExam(new UpdateRequest(1L), 1L);

        assertNotNull(response);
        assertTrue(student.getExam().contains(exam));
        verify(studentRepository, times(1)).save(student);
    }

    @Test
    void updateExam_ShouldThrowExamAlreadyPresentException() {
        Subject subject = new Subject("Math");
        Exam exam = new Exam(subject);
        exam.setId(1L);

        Student student = new Student("John Doe");
        student.setId(1L);
        student.getSubject().add(subject);
        exam.setSubject(subject);
        student.getExam().add(exam);

        when(examRepository.findById(1L)).thenReturn(Optional.of(exam));
        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));

        ExamAlreadyPresentException exception = assertThrows(
                ExamAlreadyPresentException.class,
                () -> studentService.updateExam(new UpdateRequest(1L), 1L)
        );

        assertEquals(Constant.EXAM_ALREADY_PRESENT, exception.getMessage());
    }
}
