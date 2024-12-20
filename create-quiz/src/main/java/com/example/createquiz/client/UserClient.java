package com.example.createquiz.client;

import com.example.createquiz.base.BaseResponse;
import com.example.createquiz.dto.user.UserOutputDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-service", url = "localhost:8081")
public interface UserClient {
    @GetMapping("/api/v1/users/{username}")
    BaseResponse<UserOutputDto> getUser(@PathVariable String username);
}
