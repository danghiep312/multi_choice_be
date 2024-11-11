package com.example.user.service.user_service;

import com.example.user.dto.user.UserInputDto;
import com.example.user.dto.user.UserOutputDto;
import com.example.user.exeption.ExistsException;
import com.example.user.exeption.NotFoundException;
import com.example.user.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;
    private final UserRepository userRepository;

    public UserServiceImpl(UserMapper userMapper, UserRepository userRepository) {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
    }

    @Override
    public UserOutputDto getUser(String username) {
        return userMapper.getUserOutputDtoFromUserEntity(userRepository.findByUsername(username)
                                                                       .orElseThrow(() -> new NotFoundException("Not found user with username: " + username)));
    }

    @Override
    public UserOutputDto createUser(UserInputDto userInputDto) {
        userRepository.findByUsername(userInputDto.getUsername())
                      .ifPresent(userEntity -> {
                          throw new ExistsException("User with username: " + userInputDto.getUsername() + " already exists");
                      });
        return userMapper.getUserOutputDtoFromUserEntity(userRepository.save(userMapper.getUserEntityFromUserInputDto(userInputDto)));
    }
}
