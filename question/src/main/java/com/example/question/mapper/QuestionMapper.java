package com.example.question.mapper;

import com.example.question.dto.question.QuestionInputDto;
import com.example.question.dto.question.QuestionOutputDto;
import com.example.question.entity.QuestionEntity;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface QuestionMapper {
    QuestionEntity toEntity(QuestionInputDto questionInputDto);

    QuestionOutputDto toDto(QuestionEntity questionEntity);

}
