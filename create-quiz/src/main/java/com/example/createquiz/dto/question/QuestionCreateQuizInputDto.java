package com.example.createquiz.dto.question;

import com.example.createquiz.dto.choice.ChoiceInputDto;
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
public class QuestionCreateQuizInputDto {
    @NotNull
    @NotEmpty(message = "Question text is required")
    protected String questionText;
    @NotNull
    protected List<ChoiceInputDto> choices;
    @NotNull
    protected Long order;
    @NotNull
    protected Boolean multipleChoice;
}
