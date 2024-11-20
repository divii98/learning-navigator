package com.crio.learning_navigator.exception;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class DefaultHandler {
    @JsonProperty("Error")
    String error;

    DefaultHandler(String error){
        this.error = error;
    }
}
