package com.example.config.mapper;

import com.example.config.dto.ConfigInputDto;
import com.example.config.dto.ConfigOutputDto;
import com.example.config.entitiy.ConfigEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-04-19T14:04:45+0700",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.8 (Oracle Corporation)"
)
@Component
public class ConfigMapperImpl implements ConfigMapper {

    @Override
    public ConfigEntity toEntity(ConfigInputDto configOutputDto) {
        if ( configOutputDto == null ) {
            return null;
        }

        ConfigEntity configEntity = new ConfigEntity();

        configEntity.setStartTime( configOutputDto.getStartTime() );
        configEntity.setDuration( configOutputDto.getDuration() );

        return configEntity;
    }

    @Override
    public ConfigOutputDto toDto(ConfigEntity configEntity) {
        if ( configEntity == null ) {
            return null;
        }

        ConfigOutputDto configOutputDto = new ConfigOutputDto();

        configOutputDto.setId( configEntity.getId() );
        configOutputDto.setStartTime( configEntity.getStartTime() );
        configOutputDto.setDuration( configEntity.getDuration() );

        return configOutputDto;
    }
}
