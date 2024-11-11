package com.example.auth.service.jwt;

public interface JwtService {
    String generateToken(Object subject, long expirationTime);

    void validateToken(String token);
}
