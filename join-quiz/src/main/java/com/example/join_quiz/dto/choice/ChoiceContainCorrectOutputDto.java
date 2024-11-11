package com.example.join_quiz.dto.choice;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class ChoiceContainCorrectOutputDto {
    private Long id;
    private String choiceText;
    private Long order;
    private Boolean correct;
    private Boolean selected;
}
