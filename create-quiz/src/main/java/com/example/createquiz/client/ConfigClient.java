package com.example.createquiz.client;

import com.example.createquiz.base.BaseResponse;
import com.example.createquiz.dto.config.ConfigInputDto;
import com.example.createquiz.dto.config.ConfigOutputDto;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "config-service")
public interface ConfigClient {
    @PostMapping("/api/v1/config")
    BaseResponse<ConfigOutputDto> create(@RequestBody @Valid ConfigInputDto config);
}
