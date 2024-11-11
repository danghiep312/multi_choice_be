package com.example.createquiz.controller;

import com.example.createquiz.base.BaseResponse;
import com.example.createquiz.dto.author.AuthorHeader;
import com.example.createquiz.dto.createquiz.CreateQuizInputDto;
import com.example.createquiz.dto.createquiz.CreateQuizOutputDto;
import com.example.createquiz.dto.notification.NotificationInputDto;
import com.example.createquiz.service.CreateQuizService;
import jakarta.validation.Valid;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/create-quiz")
@CrossOrigin(origins = {"*"})
public class CreateQuizController {
    private final CreateQuizService createQuizService;

    public CreateQuizController(CreateQuizService createQuizService) {
        this.createQuizService = createQuizService;
    }

    @PostMapping("/{requestId}")
    BaseResponse<CreateQuizOutputDto> create(@RequestBody @Valid CreateQuizInputDto createQuizInputDto, @RequestHeader String username, @RequestHeader String role, @PathVariable String requestId) throws InterruptedException {
        return BaseResponse.success(createQuizService.createQuiz(createQuizInputDto, requestId, new AuthorHeader(username, role)));
    }

    @GetMapping("/status/{requestId}")
    BaseResponse<String> status(@PathVariable String requestId, @RequestHeader String username) {
        return BaseResponse.success(createQuizService.status(requestId, new AuthorHeader().setUsername(username)));
    }
}
