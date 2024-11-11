package com.example.answer.service.answer_service;

import com.example.answer.dto.answer.AnswerInputDto;
import com.example.answer.dto.answer.AnswerOutputDto;
import com.example.answer.repository.AnswerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnswerServiceImpl implements AnswerService {

    private final AnswerMapper answerMapper;

    private final AnswerRepository answerRepository;

    public AnswerServiceImpl(AnswerMapper answerMapper, AnswerRepository answerRepository) {
        this.answerMapper = answerMapper;
        this.answerRepository = answerRepository;
    }

    @Override
    public AnswerOutputDto createAnswer(AnswerInputDto answerInputDto, String username, Long quizId) {

        return answerMapper.getAnswerOutputDtoFromAnswerEntity(answerRepository.save(answerMapper.getAnswerEntityFromAnswerInputDto(answerInputDto)
                                                                                                 .setUsername(username)
                                                                                                 .setQuizId(quizId)));
    }

    @Override
    public List<AnswerOutputDto> getAnswersByQuizId(Long quizId, String username) {
        return answerRepository.findByQuizIdAndUsername(quizId, username)
                               .stream()
                               .map(answerMapper::getAnswerOutputDtoFromAnswerEntity)
                               .toList();
    }
}
