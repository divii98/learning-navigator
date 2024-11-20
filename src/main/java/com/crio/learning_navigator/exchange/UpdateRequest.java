package com.crio.learning_navigator.exchange;

import com.crio.learning_navigator.constant.Constant;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateRequest {
    @NotNull(message = Constant.ID_CANNOT_BE_NULL)
    @JsonProperty("Id")
    private Long id;
}
