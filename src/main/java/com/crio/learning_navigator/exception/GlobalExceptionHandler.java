package com.crio.learning_navigator.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotExistException.class)
    public DefaultHandler notFoundExceptionHandler(NotExistException ex){
        return new DefaultHandler(ex.getMessage());
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public DefaultHandler validationExceptionHandler( MethodArgumentNotValidException ex){
        return new DefaultHandler(Objects.requireNonNull(ex.getFieldError()).getDefaultMessage());
    }
    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(SubjectAlreadyPresentException.class)
    public DefaultHandler subjectAlreadyPresentHandler(SubjectAlreadyPresentException ex){
        return new DefaultHandler(ex.getMessage());
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(ExamAlreadyPresentException.class)
    public DefaultHandler examAlreadyPresentHandler(ExamAlreadyPresentException ex){
        return new DefaultHandler(ex.getMessage());
    }

}
