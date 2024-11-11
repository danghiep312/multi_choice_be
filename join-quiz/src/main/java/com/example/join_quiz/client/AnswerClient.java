package com.example.join_quiz.client;

import com.example.join_quiz.base.BaseResponse;
import com.example.join_quiz.dto.answer.AnswerInputDto;
import com.example.join_quiz.dto.answer.AnswerOutputDto;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "answer-service")
public interface AnswerClient {
    @PostMapping("/api/v1/answer/{quizId}")
    BaseResponse<AnswerOutputDto> createAnswer(@RequestBody @Valid AnswerInputDto answerInputDto, @RequestHeader String username, @PathVariable Long quizId);

    @GetMapping("/api/v1/answer/{quizId}")
    BaseResponse<List<AnswerOutputDto>> getAnswersByQuizId(@RequestHeader String username, @PathVariable Long quizId);
}
