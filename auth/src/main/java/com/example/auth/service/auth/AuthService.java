package com.example.auth.service.auth;

import com.example.auth.dto.auth.login.LoginInputDto;
import com.example.auth.dto.auth.login.LoginOutputDto;
import com.example.auth.dto.auth.register.RegisterInputDto;
import com.example.auth.dto.auth.register.RegisterOutputDto;

public interface AuthService {
    LoginOutputDto login(LoginInputDto loginInputDto);

    RegisterOutputDto register(RegisterInputDto registerInputDto);
}
