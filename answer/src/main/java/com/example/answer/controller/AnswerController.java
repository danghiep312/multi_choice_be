package com.example.answer.controller;

import com.example.answer.base.BaseResponse;
import com.example.answer.dto.answer.AnswerInputDto;
import com.example.answer.dto.answer.AnswerOutputDto;
import com.example.answer.service.answer_service.AnswerService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/answer")
public class AnswerController {
    private final AnswerService answerService;

    public AnswerController(AnswerService answerService) {
        this.answerService = answerService;
    }

    @PostMapping("/{quizId}")
    BaseResponse<AnswerOutputDto> createAnswer(@RequestBody @Valid AnswerInputDto answerInputDto, @RequestHeader String username, @PathVariable Long quizId) {
        return BaseResponse.success(answerService.createAnswer(answerInputDto, username, quizId));
    }

    @GetMapping("/{quizId}")
    BaseResponse<List<AnswerOutputDto>> getAnswersByQuizId(@RequestHeader String username, @PathVariable Long quizId) {
        return BaseResponse.success(answerService.getAnswersByQuizId(quizId, username));
    }
}
