package com.example.question.dto.question;

import com.example.question.dto.choice.ChoiceInputDto;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class QuestionInputDto {
    @NotNull
    @NotEmpty(message = "Question text is required")
    private String questionText;
    @NotNull
    private List<ChoiceInputDto> choices;
    @NotNull
    private Long order;
    @NotNull
    private Long quizId;
    @NotNull
    private Boolean multipleChoice;
}
