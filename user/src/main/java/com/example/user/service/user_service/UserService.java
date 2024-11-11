package com.example.user.service.user_service;

import com.example.user.dto.user.UserInputDto;
import com.example.user.dto.user.UserOutputDto;

public interface UserService {
    UserOutputDto getUser(String username);
    UserOutputDto createUser(UserInputDto userInputDto);
}
