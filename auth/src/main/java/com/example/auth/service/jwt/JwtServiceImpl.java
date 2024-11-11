package com.example.auth.service.jwt;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.Date;
import java.util.Map;

@Service
public class JwtServiceImpl implements JwtService {

    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.kid}")
    private String keyId;

    private final Gson gson;

    public JwtServiceImpl(Gson gson) {
        this.gson = gson;
    }

    @Override
    public String generateToken(Object subject, long expirationTime) {
        return Jwts.builder()
                   .setExpiration(new Date(expirationTime))
                   .signWith(SignatureAlgorithm.HS256, Base64.getDecoder()
                                                             .decode(secretKey))
                   .setIssuedAt(new Date())
                   .setHeaderParam("kid", keyId)
                   .addClaims(gson.fromJson(gson.toJson(subject), Map.class))
                   .compact();
    }

    @Override
    public void validateToken(String token) {
        Jwts.parser()
            .setSigningKey(Base64.getDecoder()
                                 .decode(secretKey))
            .parseClaimsJws(token);
    }
}
