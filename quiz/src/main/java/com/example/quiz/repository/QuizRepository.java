package com.example.quiz.repository;

import com.example.quiz.entity.QuizEntity;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


@Repository
public interface QuizRepository  extends JpaRepository<QuizEntity, Long>{
    Optional<QuizEntity> findById(Long id);
    void deleteById(Long id);
}
