package com.example.createquiz.client;

import com.example.createquiz.base.BaseResponse;
import com.example.createquiz.dto.question.QuestionInputDto;
import com.example.createquiz.dto.question.QuestionOutputDto;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "question-service")
public interface QuestionClient {
    @PostMapping("/api/v1/questions")
    BaseResponse<QuestionOutputDto> create(@RequestBody @Valid QuestionInputDto question);

    @GetMapping("/api/v1/questions/{id}")
    BaseResponse<QuestionOutputDto> findById(@PathVariable Long id);

    @GetMapping("/api/v1/questions/quiz/{quizId}")
    BaseResponse<List<QuestionOutputDto>> getQuestionsByQuiz(@PathVariable Long quizId);
}
