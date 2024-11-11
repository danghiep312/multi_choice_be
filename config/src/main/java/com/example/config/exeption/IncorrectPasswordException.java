package com.example.config.exeption;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IncorrectPasswordException extends RuntimeException {
    private String message;
}
