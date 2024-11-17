package com.example.quiz.mapper;

import com.example.quiz.dto.QuizInputDto;
import com.example.quiz.dto.QuizOutputDto;
import com.example.quiz.entity.QuizEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-17T23:32:17+0700",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.12 (Amazon.com Inc.)"
)
@Component
public class QuizMapperImpl implements QuizMapper {

    @Override
    public QuizEntity toEntity(QuizInputDto quizInputDto) {
        if ( quizInputDto == null ) {
            return null;
        }

        QuizEntity quizEntity = new QuizEntity();

        quizEntity.setName( quizInputDto.getName() );
        quizEntity.setDescription( quizInputDto.getDescription() );
        quizEntity.setConfigId( quizInputDto.getConfigId() );
        quizEntity.setAuthorName( quizInputDto.getAuthorName() );

        return quizEntity;
    }

    @Override
    public QuizOutputDto toDto(QuizEntity quizEntity) {
        if ( quizEntity == null ) {
            return null;
        }

        QuizOutputDto quizOutputDto = new QuizOutputDto();

        quizOutputDto.setId( quizEntity.getId() );
        quizOutputDto.setName( quizEntity.getName() );
        quizOutputDto.setDescription( quizEntity.getDescription() );
        quizOutputDto.setConfigId( quizEntity.getConfigId() );
        quizOutputDto.setAuthorName( quizEntity.getAuthorName() );

        return quizOutputDto;
    }
}
