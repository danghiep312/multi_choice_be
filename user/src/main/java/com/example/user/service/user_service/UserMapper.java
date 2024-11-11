package com.example.user.service.user_service;

import com.example.user.dto.user.UserInputDto;
import com.example.user.dto.user.UserOutputDto;
import com.example.user.entity.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserOutputDto getUserOutputDtoFromUserEntity(UserEntity userEntity);

    UserEntity getUserEntityFromUserInputDto(UserInputDto userInputDto);
}
