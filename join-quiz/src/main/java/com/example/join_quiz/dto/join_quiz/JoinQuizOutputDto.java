package com.example.join_quiz.dto.join_quiz;

import com.example.join_quiz.dto.config.ConfigOutputDto;
import com.example.join_quiz.dto.question.QuestionOutputDto;
import com.example.join_quiz.dto.quiz.QuizOutputDto;
import com.example.join_quiz.dto.user.UserOutputDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class JoinQuizOutputDto {
    private Long id;
    private String name;
    private String description;
    private ConfigOutputDto config;
    private List<QuestionOutputDto> questions;
}
