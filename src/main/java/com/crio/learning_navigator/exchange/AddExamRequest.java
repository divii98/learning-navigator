package com.crio.learning_navigator.exchange;

import com.crio.learning_navigator.constant.Constant;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AddExamRequest {
    @NotNull(message = Constant.ID_CANNOT_BE_NULL)
    @JsonProperty("Subject_ID")
    private Long id;
}
