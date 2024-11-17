package com.example.user.controller;

import com.example.user.base.BaseResponse;
import com.example.user.dto.user.UserInputDto;
import com.example.user.dto.user.UserOutputDto;
import com.example.user.service.user_service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{username}")
    BaseResponse<UserOutputDto> getUser(@PathVariable String username) {
        return BaseResponse.success(userService.getUser(username));
    }

    @PostMapping
    BaseResponse<UserOutputDto> createUser(@RequestBody UserInputDto userInputDto) {
        return BaseResponse.success(userService.createUser(userInputDto));
    }

    @GetMapping("/hello")
    BaseResponse<String> hello() {
        return BaseResponse.success("Hello");
    }
}
