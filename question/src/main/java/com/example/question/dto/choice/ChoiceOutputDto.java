package com.example.question.dto.choice;

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
    private Long order;
    private String choiceText;
    private Boolean correct;
}
