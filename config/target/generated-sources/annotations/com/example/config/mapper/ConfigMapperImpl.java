package com.example.config.mapper;

import com.example.config.dto.ConfigInputDto;
import com.example.config.dto.ConfigOutputDto;
import com.example.config.entity.ConfigEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-25T00:46:27+0700",
    comments = "version: 1.5.3.Final, compiler: Eclipse JDT (IDE) 3.40.0.z20241023-1306, environment: Java 17.0.13 (Eclipse Adoptium)"
)
@Component
public class ConfigMapperImpl implements ConfigMapper {

    @Override
    public ConfigEntity toEntity(ConfigInputDto configOutputDto) {
        if ( configOutputDto == null ) {
            return null;
        }

        ConfigEntity configEntity = new ConfigEntity();

        configEntity.setDuration( configOutputDto.getDuration() );
        configEntity.setStartTime( configOutputDto.getStartTime() );

        return configEntity;
    }

    @Override
    public ConfigOutputDto toDto(ConfigEntity configEntity) {
        if ( configEntity == null ) {
            return null;
        }

        ConfigOutputDto configOutputDto = new ConfigOutputDto();

        configOutputDto.setDuration( configEntity.getDuration() );
        configOutputDto.setId( configEntity.getId() );
        configOutputDto.setStartTime( configEntity.getStartTime() );

        return configOutputDto;
    }
}
