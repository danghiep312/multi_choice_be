package com.example.createquiz.dto.createquiz;

import com.example.createquiz.dto.config.ConfigOutputDto;
import com.example.createquiz.dto.question.QuestionOutputDto;
import com.example.createquiz.dto.quiz.QuizOutputDto;
import com.example.createquiz.dto.user.UserOutputDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class CreateQuizOutputDto {
    private Long id;
    private String name;
    private String description;
    private List<UserOutputDto> users;
    private ConfigOutputDto config;
    private List<QuestionOutputDto> questions;
}
