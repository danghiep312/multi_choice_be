package com.example.auth.exeption;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvalidTokenException extends RuntimeException {
    private String message;
}