package com.example.question.service;

import com.example.question.dto.question.QuestionInputDto;
import com.example.question.dto.question.QuestionOutputDto;

import java.util.List;

public interface QuestionService {
    QuestionOutputDto create(QuestionInputDto questionDto);

    QuestionOutputDto findById(Long id);

    List<QuestionOutputDto> getQuestionsByQuiz(Long quizId);
}
