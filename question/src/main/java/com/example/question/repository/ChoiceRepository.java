package com.example.question.repository;

import com.example.question.entity.ChoiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChoiceRepository extends JpaRepository<ChoiceEntity, Long> {
    List<ChoiceEntity> findByQuestionIdOrderByOrderAsc(Long questionId);
}
