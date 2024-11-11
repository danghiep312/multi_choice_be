package com.example.join_quiz.client;

import com.example.join_quiz.base.BaseResponse;
import com.example.join_quiz.dto.user.UserOutputDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "user-service")
public interface UserClient {
    @GetMapping("/api/v1/users/{username}")
    BaseResponse<UserOutputDto> getUser(@PathVariable String username);
}
