package com.example.join_quiz.repository;

import com.example.join_quiz.entity.CandidateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidateRepository extends JpaRepository<CandidateEntity, Long> {
    Boolean existsByQuizIdAndUsername(Long quizId, String username);
}
