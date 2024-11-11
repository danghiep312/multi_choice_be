package com.example.quiz.mapper;

import com.example.quiz.dto.QuizInputDto;
import com.example.quiz.dto.QuizOutputDto;
import com.example.quiz.entity.QuizEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface QuizMapper {
    QuizEntity toEntity(QuizInputDto quizInputDto);

    QuizOutputDto toDto(QuizEntity quizEntity);
}
