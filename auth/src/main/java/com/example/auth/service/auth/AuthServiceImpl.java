package com.example.auth.service.auth;

import com.example.auth.client.UserClient;
import com.example.auth.dto.auth.login.AccessTokenSubject;
import com.example.auth.dto.auth.login.LoginInputDto;
import com.example.auth.dto.auth.login.LoginOutputDto;
import com.example.auth.dto.auth.login.RefreshTokenSubject;
import com.example.auth.dto.auth.register.RegisterInputDto;
import com.example.auth.dto.auth.register.RegisterOutputDto;
import com.example.auth.dto.user.UserInputDto;
import com.example.auth.dto.user.UserOutputDto;
import com.example.auth.entity.AccountEntity;
import com.example.auth.entity.Role;
import com.example.auth.exeption.ExistsException;
import com.example.auth.exeption.NotFoundException;
import com.example.auth.repository.AccountRepository;
import com.example.auth.service.jwt.JwtService;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AuthServiceImpl implements AuthService {
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AccountRepository accountRepository;
    private final UserClient userClient;

    private static final Long accessTokenExpiry = 1000L * 60 * 60 * 24 * 21;

    public AuthServiceImpl(PasswordEncoder passwordEncoder, JwtService jwtService, AccountRepository accountRepository, UserClient userClient) {
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.accountRepository = accountRepository;
        this.userClient = userClient;
    }

    @Override
    public LoginOutputDto login(LoginInputDto loginInputDto) {
        AccountEntity accountEntity = accountRepository.findByUsername(loginInputDto.getUsername())
                                                       .orElseThrow(() -> new NotFoundException("Not found account with username: " + loginInputDto.getUsername()));
        if (!passwordEncoder.matches(loginInputDto.getPassword(), accountEntity.getPassword())) {
            throw new NotFoundException("Password is incorrect");
        }
        UserOutputDto userOutputDto = userClient.getUser(loginInputDto.getUsername())
                                                .getData();
        LoginOutputDto loginOutputDto = new LoginOutputDto();
        Date now = new Date();
        long accessExp = now.getTime() + accessTokenExpiry;
        long refreshExp = now.getTime() + 1000L * 60 * 60 * 24 * 7;
        loginOutputDto.setAccessToken(jwtService.generateToken(new AccessTokenSubject().setUsername(accountEntity.getUsername())
                                                                                       .setRole(accountEntity.getRole()
                                                                                                             .name()), accessExp))
                      .setRole(accountEntity.getRole()
                                            .name())
                      .setFullName(userOutputDto.getFullName())
                      .setRefreshToken(jwtService.generateToken(new RefreshTokenSubject().setUsername(accountEntity.getUsername()), refreshExp))
                      .setUsername(accountEntity.getUsername());
        return loginOutputDto;
    }

    @Transactional
    @Override
    public RegisterOutputDto register(RegisterInputDto registerInputDto) {
        accountRepository.findByUsername(registerInputDto.getUsername())
                         .ifPresent(accountEntity -> {
                             throw new ExistsException("Account with username: " + registerInputDto.getUsername() + " already exists");
                         });
        UserInputDto userInputDto = new UserInputDto();
        userInputDto.setUsername(registerInputDto.getUsername());
        userInputDto.setFullName(registerInputDto.getFullName());
        userInputDto.setChatId(registerInputDto.getChatId());
        userClient.createUser(userInputDto);
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setUsername(registerInputDto.getUsername());
        accountEntity.setPassword(passwordEncoder.encode(registerInputDto.getPassword()));
        accountEntity.setRole(Role.STUDENT);
        accountRepository.save(accountEntity);
        return new RegisterOutputDto().setUsername(registerInputDto.getUsername())
                                      .setChatId(registerInputDto.getChatId())
                                      .setFullName(registerInputDto.getFullName());
    }
}
