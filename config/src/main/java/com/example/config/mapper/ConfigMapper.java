package com.example.config.mapper;

import com.example.config.dto.ConfigInputDto;
import com.example.config.dto.ConfigOutputDto;
import com.example.config.entitiy.ConfigEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ConfigMapper {

    ConfigEntity toEntity(ConfigInputDto configOutputDto);

    ConfigOutputDto toDto(ConfigEntity configEntity);
}
