package com.crio.learning_navigator.exchange;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class HiddenResponse {
    @JsonProperty("number")
    String number;
    @JsonProperty("fact")
    String fact;
}
