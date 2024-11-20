package com.crio.learning_navigator.exception;

public class SubjectAlreadyPresentException extends RuntimeException {
    public SubjectAlreadyPresentException(String msg){
        super(msg);
    }
}
