package com.crio.learning_navigator.exchange;

import com.fasterxml.jackson.annotation.JsonProperty;

public class HiddenResponse {
    @JsonProperty("Number")
    Integer number;
    @JsonProperty("Fact")
    String fact;
}
