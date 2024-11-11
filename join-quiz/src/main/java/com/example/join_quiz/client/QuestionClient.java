package com.example.join_quiz.client;

import com.example.join_quiz.base.BaseResponse;
import com.example.join_quiz.dto.question.QuestionContainCorrectOutputDto;
import com.example.join_quiz.dto.question.QuestionOutputDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "question-service")
public interface QuestionClient {
    @GetMapping("/api/v1/questions/{id}")
    BaseResponse<QuestionOutputDto> findById(@PathVariable Long id);

    @GetMapping("/api/v1/questions/quiz/{quizId}")
    BaseResponse<List<QuestionOutputDto>> getQuestionsByQuiz(@PathVariable Long quizId);

    @GetMapping("/api/v1/questions/quiz/{quizId}")
    BaseResponse<List<QuestionContainCorrectOutputDto>> getQuestionContainCorrectByQuiz(@PathVariable Long quizId);
}
