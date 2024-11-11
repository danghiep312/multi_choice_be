package com.example.question.service;


import com.example.question.dto.choice.ChoiceOutputDto;
import com.example.question.dto.question.QuestionInputDto;
import com.example.question.dto.question.QuestionOutputDto;
import com.example.question.entity.ChoiceEntity;
import com.example.question.entity.QuestionEntity;
import com.example.question.exeption.NotFoundException;
import com.example.question.mapper.ChoiceMapper;
import com.example.question.mapper.QuestionMapper;
import com.example.question.repository.ChoiceRepository;
import com.example.question.repository.QuestionRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {
    private final QuestionRepository questionRepository;
    private final QuestionMapper questionMapper;
    private final ChoiceMapper choiceMapper;
    private final ChoiceRepository choiceRepository;

    public QuestionServiceImpl(QuestionRepository questionRepository, QuestionMapper questionMapper, ChoiceMapper choiceMapper, ChoiceRepository choiceRepository) {
        this.questionRepository = questionRepository;
        this.questionMapper = questionMapper;
        this.choiceMapper = choiceMapper;
        this.choiceRepository = choiceRepository;
    }

    @Transactional
    @Override
    public QuestionOutputDto create(QuestionInputDto questionDto) {
        QuestionEntity questionEntity = questionMapper.toEntity(questionDto);
        QuestionEntity savedQuestion = questionRepository.save(questionEntity);
        List<ChoiceOutputDto> choices = questionDto.getChoices()
                                                   .stream()
                                                   .map(choice -> {
                                                       ChoiceEntity choiceEntity = choiceMapper.getChoiceEntityFromChoiceInputDto(choice);
                                                       choiceEntity.setQuestionId(savedQuestion.getId());
                                                       return choiceMapper.getChoiceOutputDtoFromChoiceEntity(choiceRepository.save(choiceEntity));
                                                   })
                                                   .toList();
        QuestionOutputDto questionOutputDto = questionMapper.toDto(savedQuestion);
        questionOutputDto.setChoices(choices);
        return questionOutputDto;
    }

    @Override
    public QuestionOutputDto findById(Long id) {
        return questionRepository.findById(id)
                                 .map(questionMapper::toDto)
                                 .map(questionOutputDto -> questionOutputDto.setChoices(choiceRepository.findByQuestionIdOrderByOrderAsc(id)
                                                                                                        .stream()
                                                                                                        .map(choiceMapper::getChoiceOutputDtoFromChoiceEntity)
                                                                                                        .toList()))
                                 .orElseThrow(() -> new NotFoundException("Question not found"));
    }

    @Override
    public List<QuestionOutputDto> getQuestionsByQuiz(Long quizId) {
        return questionRepository.findAllByQuizIdOrderByOrderAsc(quizId)
                                 .stream()
                                 .map(questionMapper::toDto)
                                 .map(questionOutputDto -> questionOutputDto.setChoices(choiceRepository.findByQuestionIdOrderByOrderAsc(questionOutputDto.getId())
                                                                                                        .stream()
                                                                                                        .map(choiceMapper::getChoiceOutputDtoFromChoiceEntity)
                                                                                                        .toList()))
                                 .toList();
    }
}
