package com.example.join_quiz.dto.answer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class AnswerInputDto {
    @NonNull
    private Long questionId;
    @NonNull
    private Long choiceId;
    @NonNull
    private Double score;
}
