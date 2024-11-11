package com.example.question.dto.question;

import com.example.question.dto.choice.ChoiceOutputDto;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class QuestionOutputDto {
    private Long id;
    private String questionText;
    private List<ChoiceOutputDto> choices;
    private Long order;
    private Long quizId;
    private Boolean multipleChoice;
}
