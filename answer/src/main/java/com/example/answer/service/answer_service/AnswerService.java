package com.example.answer.service.answer_service;

import com.example.answer.dto.answer.AnswerInputDto;
import com.example.answer.dto.answer.AnswerOutputDto;

import java.util.List;

public interface AnswerService {
    AnswerOutputDto createAnswer(AnswerInputDto answerInputDto, String username, Long quizId);

    List<AnswerOutputDto> getAnswersByQuizId(Long quizId, String username);
}
