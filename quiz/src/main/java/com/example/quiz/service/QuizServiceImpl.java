package com.example.quiz.service;


import com.example.quiz.dto.QuizInputDto;
import com.example.quiz.dto.QuizOutputDto;
import com.example.quiz.entity.QuizEntity;
import com.example.quiz.exeption.NotFoundException;
import com.example.quiz.mapper.QuizMapper;
import com.example.quiz.repository.QuizRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizServiceImpl implements QuizService {
    private final QuizRepository quizRepository;
    private final QuizMapper quizMapper;

    public QuizServiceImpl(QuizRepository quizRepository, QuizMapper quizMapper) {
        this.quizRepository = quizRepository;
        this.quizMapper = quizMapper;
    }


    @Override
    public QuizOutputDto findById(Long id) {
        return quizRepository.findById(id)
                             .map(quizMapper::toDto)
                             .orElseThrow(() -> new NotFoundException("Quiz not found"));
    }


    @Override
    public QuizOutputDto create(QuizInputDto quizInputDto) {
        QuizEntity quizEntity = quizMapper.toEntity(quizInputDto);
        quizRepository.save(quizEntity);
        return quizMapper.toDto(quizEntity);
    }
}
