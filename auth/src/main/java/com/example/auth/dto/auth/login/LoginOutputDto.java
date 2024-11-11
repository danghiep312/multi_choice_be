package com.example.auth.dto.auth.login;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class LoginOutputDto {
    private String username;
    private String accessToken;
    private String refreshToken;
    private String fullName;
    private String role;
}
