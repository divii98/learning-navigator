package com.crio.learning_navigator.exception;

import jakarta.validation.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.method.MethodValidationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Objects;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(NotExistException.class)
    public ResponseEntity<DefaultHandler> notFoundExceptionHandler(NotExistException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new DefaultHandler(ex.getMessage()));
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<DefaultHandler> validationExceptionHandler( MethodArgumentNotValidException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new DefaultHandler(Objects.requireNonNull(ex.getFieldError()).getDefaultMessage()));
    }

}
