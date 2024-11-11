package com.example.config.controller;

import com.example.config.base.BaseResponse;
import com.example.config.dto.ConfigInputDto;
import com.example.config.dto.ConfigOutputDto;
import com.example.config.service.ConfigService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/config")
public class ConfigController {
    private final ConfigService configService;

    @GetMapping("/{id}")
    BaseResponse<ConfigOutputDto> findById(@PathVariable Long id) {
        return BaseResponse.success(configService.findById(id));
    }

    @PostMapping()
    BaseResponse<ConfigOutputDto> create(@RequestBody @Valid ConfigInputDto config) {
        return BaseResponse.success(configService.create(config));
    }
}
