package com.example.createquiz.dto.question;

import com.example.createquiz.dto.choice.ChoiceOutputDto;
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
    private Boolean multipleChoice;
}
