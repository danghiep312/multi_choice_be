package com.example.join_quiz.dto.user_choice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class UserChoiceInputDto {
    private Long questionId;
    private Long choiceId;
}
