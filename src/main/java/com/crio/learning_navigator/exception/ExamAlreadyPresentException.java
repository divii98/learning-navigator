package com.crio.learning_navigator.exception;

public class ExamAlreadyPresentException extends RuntimeException {
    public ExamAlreadyPresentException(String msg){
        super(msg);
    }
}
