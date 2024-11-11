package com.example.config.service;


import com.example.config.dto.ConfigInputDto;
import com.example.config.dto.ConfigOutputDto;

import java.util.List;

public interface ConfigService {

    ConfigOutputDto findById(Long id);

    ConfigOutputDto create(ConfigInputDto configInputDto);
}
