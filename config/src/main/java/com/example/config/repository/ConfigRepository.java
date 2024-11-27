package com.example.config.repository;


import com.example.config.entity.ConfigEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ConfigRepository extends JpaRepository<ConfigEntity, Long> {

    Optional<ConfigEntity> findById(Long id);

    void deleteById(Long id);
}

