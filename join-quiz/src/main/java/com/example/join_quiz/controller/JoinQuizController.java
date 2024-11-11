package com.example.join_quiz.controller;

import com.example.join_quiz.base.BaseResponse;
import com.example.join_quiz.dto.join_quiz.JoinQuizOutputDto;
import com.example.join_quiz.dto.submit.SubmitInputDto;
import com.example.join_quiz.dto.submit.SubmitOutputDto;
import com.example.join_quiz.service.JoinQuizService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/join-quiz")
@CrossOrigin(origins = {"*"})
public class JoinQuizController {
    private final JoinQuizService joinQuizService;

    public JoinQuizController(JoinQuizService joinQuizService) {
        this.joinQuizService = joinQuizService;
    }

    @GetMapping("/{quizId}/{requestId}")
    BaseResponse<JoinQuizOutputDto> join(@PathVariable Long quizId, @RequestHeader String username, @PathVariable String requestId) throws InterruptedException {
        return BaseResponse.success(joinQuizService.joinQuiz(quizId, username, requestId));
    }

    @PostMapping("/submit/{quizId}/{requestId}")
    BaseResponse<SubmitOutputDto> submit(@PathVariable Long quizId, @RequestBody @Valid SubmitInputDto submitInputDto, @RequestHeader String username, @PathVariable String requestId) throws InterruptedException {
        return BaseResponse.success(joinQuizService.submit(submitInputDto, username, quizId, requestId));
    }

    @GetMapping("/status/{requestId}")
    BaseResponse<String> getStatus(@PathVariable String requestId, @RequestHeader String username) {
        return BaseResponse.success(joinQuizService.status(requestId, username));
    }
}
