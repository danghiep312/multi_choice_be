package com.example.createquiz.dto.createquiz;

import com.example.createquiz.dto.config.ConfigInputDto;
import com.example.createquiz.dto.question.QuestionCreateQuizInputDto;
import com.example.createquiz.dto.question.QuestionInputDto;
import com.example.createquiz.dto.quiz.QuizInputDto;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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
public class CreateQuizInputDto {
    @NotNull
    @NotEmpty
    private String name;
    private String description;
    @NotNull
    private ConfigInputDto config;
    @NotNull
    private List<String> users;
    @NotNull
    private List<QuestionCreateQuizInputDto> questions;
}
