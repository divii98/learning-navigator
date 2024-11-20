package com.crio.learning_navigator.exchange;

import com.crio.learning_navigator.constant.Constant;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PostAddRequest {
    @NotBlank(message = Constant.NAME_CANNOT_BE_BLANK)
    @JsonProperty("Name")
    private String name;
}
