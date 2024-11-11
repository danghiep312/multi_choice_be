package com.example.auth.controller;

import com.example.auth.base.BaseResponse;
import com.example.auth.dto.auth.login.LoginInputDto;
import com.example.auth.dto.auth.login.LoginOutputDto;
import com.example.auth.dto.auth.register.RegisterInputDto;
import com.example.auth.dto.auth.register.RegisterOutputDto;
import com.example.auth.service.auth.AuthService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin(origins = {"*"})
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    BaseResponse<LoginOutputDto> login(@RequestBody LoginInputDto loginInputDto) {
        return BaseResponse.success(authService.login(loginInputDto));
    }

    @PostMapping("register")
    BaseResponse<RegisterOutputDto> register(@RequestBody RegisterInputDto registerInputDto) {
        return BaseResponse.success(authService.register(registerInputDto));
    }
}
