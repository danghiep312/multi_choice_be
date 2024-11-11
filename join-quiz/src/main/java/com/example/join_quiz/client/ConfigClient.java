package com.example.join_quiz.client;

import com.example.join_quiz.base.BaseResponse;
import com.example.join_quiz.dto.config.ConfigOutputDto;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "config-service")
public interface ConfigClient {
    @GetMapping("/api/v1/config/{id}")
    BaseResponse<ConfigOutputDto> findById(@PathVariable Long id);
}
