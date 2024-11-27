package com.example.quiz.controller;


import com.example.quiz.base.BaseResponse;
import com.example.quiz.dto.QuizInputDto;
import com.example.quiz.dto.QuizOutputDto;
import com.example.quiz.service.QuizService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/quiz")
public class QuizController {
    private final QuizService quizService;

    @GetMapping("/{id}")
    BaseResponse<QuizOutputDto> findById(@PathVariable Long id) {
        return BaseResponse.success(quizService.findById(id));
    }

    @PostMapping()
    BaseResponse<QuizOutputDto> create(@RequestBody @Valid QuizInputDto quiz) {
        return BaseResponse.success(quizService.create(quiz));
    }

    @GetMapping("/hello")
    BaseResponse<String> hello() {
        return BaseResponse.success("Hello from quiz");
    }
}
