package com.example.answer.service.answer_service;

import com.example.answer.dto.answer.AnswerInputDto;
import com.example.answer.dto.answer.AnswerOutputDto;
import com.example.answer.entity.AnswerEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AnswerMapper {
    AnswerOutputDto getAnswerOutputDtoFromAnswerEntity(AnswerEntity answerEntity);

    AnswerEntity getAnswerEntityFromAnswerInputDto(AnswerInputDto answerInputDto);
}
