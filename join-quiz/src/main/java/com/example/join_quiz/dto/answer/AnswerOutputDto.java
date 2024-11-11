package com.example.join_quiz.dto.answer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class AnswerOutputDto {
    private Long id;
    private Long quizId;
    private Long questionId;
    private Long choiceId;
    private String username;
    private Double score;
}
