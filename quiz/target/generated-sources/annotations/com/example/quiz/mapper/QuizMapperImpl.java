package com.example.quiz.mapper;

import com.example.quiz.dto.QuizInputDto;
import com.example.quiz.dto.QuizOutputDto;
import com.example.quiz.entity.QuizEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-25T00:46:35+0700",
    comments = "version: 1.5.3.Final, compiler: Eclipse JDT (IDE) 3.40.0.z20241023-1306, environment: Java 17.0.13 (Eclipse Adoptium)"
)
@Component
public class QuizMapperImpl implements QuizMapper {

    @Override
    public QuizEntity toEntity(QuizInputDto quizInputDto) {
        if ( quizInputDto == null ) {
            return null;
        }

        QuizEntity quizEntity = new QuizEntity();

        quizEntity.setAuthorName( quizInputDto.getAuthorName() );
        quizEntity.setConfigId( quizInputDto.getConfigId() );
        quizEntity.setDescription( quizInputDto.getDescription() );
        quizEntity.setName( quizInputDto.getName() );

        return quizEntity;
    }

    @Override
    public QuizOutputDto toDto(QuizEntity quizEntity) {
        if ( quizEntity == null ) {
            return null;
        }

        QuizOutputDto quizOutputDto = new QuizOutputDto();

        quizOutputDto.setAuthorName( quizEntity.getAuthorName() );
        quizOutputDto.setConfigId( quizEntity.getConfigId() );
        quizOutputDto.setDescription( quizEntity.getDescription() );
        quizOutputDto.setId( quizEntity.getId() );
        quizOutputDto.setName( quizEntity.getName() );

        return quizOutputDto;
    }
}
