package com.example.createquiz.service;

import com.example.createquiz.dto.author.AuthorHeader;
import com.example.createquiz.dto.createquiz.CreateQuizInputDto;
import com.example.createquiz.dto.createquiz.CreateQuizOutputDto;

public interface CreateQuizService {
    CreateQuizOutputDto createQuiz(CreateQuizInputDto createQuizInputDto, String requestId, AuthorHeader authorHeader) throws InterruptedException;

    String status(String requestId, AuthorHeader authorHeader);
}
