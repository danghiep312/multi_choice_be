package com.example.quiz.service;

import com.example.quiz.dto.QuizInputDto;
import com.example.quiz.dto.QuizOutputDto;

import java.util.List;

public interface QuizService {
    QuizOutputDto findById(Long id);
    QuizOutputDto create(QuizInputDto quizInputDto);
}
