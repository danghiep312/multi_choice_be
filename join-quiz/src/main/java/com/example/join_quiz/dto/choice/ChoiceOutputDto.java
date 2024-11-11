package com.example.join_quiz.dto.choice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class ChoiceOutputDto {
    private Long id;
    private String choiceText;
    private Long order;
}
