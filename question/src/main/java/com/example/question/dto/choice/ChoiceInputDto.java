package com.example.question.dto.choice;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class ChoiceInputDto {
    @NotNull
    @NotEmpty(message = "Choice text is required")
    private String choiceText;
    @NotNull
    private Boolean correct;
    @NotNull
    private Long order;
}
