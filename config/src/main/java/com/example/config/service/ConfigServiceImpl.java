package com.example.config.service;

import com.example.config.dto.ConfigInputDto;
import com.example.config.dto.ConfigOutputDto;
import com.example.config.entity.ConfigEntity;
import com.example.config.exeption.NotFoundException;
import com.example.config.mapper.ConfigMapper;
import com.example.config.repository.ConfigRepository;
import org.springframework.stereotype.Service;

@Service
public class ConfigServiceImpl implements ConfigService {

    private final ConfigRepository configRepository;
    private final ConfigMapper configMapper;

    public ConfigServiceImpl(ConfigRepository configRepository, ConfigMapper configMapper) {
        this.configRepository = configRepository;
        this.configMapper = configMapper;
    }

    @Override
    public ConfigOutputDto findById(Long id) {
        return configRepository.findById(id)
                               .map(configMapper::toDto)
                               .orElseThrow(() -> new NotFoundException("Config not found"));
    }


    @Override
    public ConfigOutputDto create(ConfigInputDto configInputDto) {
        ConfigEntity configEntity = configMapper.toEntity(configInputDto);
        configRepository.save(configEntity);
        return configMapper.toDto(configEntity);
    }
}
