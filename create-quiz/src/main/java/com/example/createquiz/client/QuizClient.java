package com.example.createquiz.client;

import com.example.createquiz.base.BaseResponse;
import com.example.createquiz.dto.quiz.QuizInputDto;
import com.example.createquiz.dto.quiz.QuizOutputDto;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "quiz-service", url = "localhost:8086")
public interface QuizClient {
    @PostMapping("/api/v1/quiz")
    BaseResponse<QuizOutputDto> create(@RequestBody @Valid QuizInputDto quiz);
}
