package com.example.join_quiz.service;

import com.example.join_quiz.dto.join_quiz.JoinQuizOutputDto;
import com.example.join_quiz.dto.submit.SubmitInputDto;
import com.example.join_quiz.dto.submit.SubmitOutputDto;

public interface JoinQuizService {
    JoinQuizOutputDto joinQuiz(Long quizId, String username, String requestId) throws InterruptedException;

    SubmitOutputDto submit(SubmitInputDto submitInputDto, String username, Long quizId, String requestId) throws InterruptedException;

    String status(String requestId, String username);
}
