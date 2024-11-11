package com.example.question.controller;

import com.example.question.base.BaseResponse;
import com.example.question.dto.question.QuestionInputDto;
import com.example.question.dto.question.QuestionOutputDto;
import com.example.question.service.QuestionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/questions")
public class QuestionController {
    private final QuestionService questionService;

    @PostMapping()
    BaseResponse<QuestionOutputDto> create(@RequestBody @Valid QuestionInputDto question) {
        return BaseResponse.success(questionService.create(question));
    }

    @GetMapping("/{id}")
    BaseResponse<QuestionOutputDto> findById(@PathVariable Long id) {
        return BaseResponse.success(questionService.findById(id));
    }

    @GetMapping("/quiz/{quizId}")
    BaseResponse<List<QuestionOutputDto>> getQuestionsByQuiz(@PathVariable Long quizId) {
        return BaseResponse.success(questionService.getQuestionsByQuiz(quizId));
    }

}
