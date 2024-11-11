package com.example.question.mapper;

import com.example.question.dto.choice.ChoiceInputDto;
import com.example.question.dto.choice.ChoiceOutputDto;
import com.example.question.entity.ChoiceEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ChoiceMapper {
    ChoiceOutputDto getChoiceOutputDtoFromChoiceEntity(ChoiceEntity choiceEntity);

    ChoiceEntity getChoiceEntityFromChoiceInputDto(ChoiceInputDto choiceInputDto);
}
