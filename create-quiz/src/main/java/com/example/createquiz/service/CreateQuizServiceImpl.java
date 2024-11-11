package com.example.createquiz.service;

import com.example.createquiz.client.ConfigClient;
import com.example.createquiz.client.QuestionClient;
import com.example.createquiz.client.QuizClient;
import com.example.createquiz.client.UserClient;
import com.example.createquiz.dto.author.AuthorHeader;
import com.example.createquiz.dto.config.ConfigOutputDto;
import com.example.createquiz.dto.createquiz.CreateQuizInputDto;
import com.example.createquiz.dto.createquiz.CreateQuizOutputDto;
import com.example.createquiz.dto.notification.NotificationInputDto;
import com.example.createquiz.dto.question.QuestionInputDto;
import com.example.createquiz.dto.question.QuestionOutputDto;
import com.example.createquiz.dto.quiz.QuizInputDto;
import com.example.createquiz.dto.quiz.QuizOutputDto;
import com.example.createquiz.dto.user.UserOutputDto;
import com.example.createquiz.entity.CandidateEntity;
import com.example.createquiz.entity.Request;
import com.example.createquiz.exeption.ForbiddenException;
import com.example.createquiz.repository.CandidateRepository;
import jakarta.transaction.Transactional;
import org.redisson.api.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class CreateQuizServiceImpl implements CreateQuizService {
    private final ConfigClient configClient;
    private final QuizClient quizClient;
    private final UserClient userClient;
    private final QuestionClient questionClient;
    private final CandidateRepository candidateRepository;
    private final RMapCache<Object, Object> requestMap;

    private final KafkaTemplate<String, NotificationInputDto> kafkaTemplate;

    @Value("${fe.host}")
    private String feHost;

    public CreateQuizServiceImpl(ConfigClient configClient, QuizClient quizClient, UserClient userClient, QuestionClient questionClient, CandidateRepository candidateRepository, RedissonClient redissonClient, KafkaTemplate<String, NotificationInputDto> kafkaTemplate) {
        this.configClient = configClient;
        this.quizClient = quizClient;
        this.userClient = userClient;
        this.questionClient = questionClient;
        this.candidateRepository = candidateRepository;
        this.requestMap = redissonClient.getMapCache("create-quiz");
        this.kafkaTemplate = kafkaTemplate;
    }

    @Transactional
    @Override
    public CreateQuizOutputDto createQuiz(CreateQuizInputDto createQuizInputDto, String requestId, AuthorHeader authorHeader) throws InterruptedException {
        Request request = new Request().setUsername(authorHeader.getUsername())
                                       .setStatus("Checking role");
        requestMap.put(requestId, request, 60, TimeUnit.MINUTES);
        TimeUnit.SECONDS.sleep(1);
        if (!authorHeader.getRole()
                         .equals("TEACHER")) {
            requestMap.put(requestId, request.setStatus("Forbidden"), 60, TimeUnit.MINUTES);
            throw new ForbiddenException("Only teacher can create quiz");
        }
        ConfigOutputDto configOutputDto;
        try {
            requestMap.put(requestId, request.setStatus("Creating config"), 60, TimeUnit.MINUTES);
            TimeUnit.SECONDS.sleep(1);
            configOutputDto = configClient.create(createQuizInputDto.getConfig())
                                          .getData();
        } catch (Exception e) {
            requestMap.put(requestId, request.setStatus("Error creating config"), 60, TimeUnit.MINUTES);
            throw e;
        }
        QuizOutputDto quizOutputDto;
        try {
            requestMap.put(requestId, request.setStatus("Creating quiz"), 60, TimeUnit.MINUTES);
            TimeUnit.SECONDS.sleep(1);
            quizOutputDto = quizClient.create(new QuizInputDto().setName(createQuizInputDto.getName())
                                                                .setDescription(createQuizInputDto.getDescription())
                                                                .setConfigId(configOutputDto.getId())
                                                                .setAuthorName(authorHeader.getUsername()))
                                      .getData();
        } catch (Exception e) {
            requestMap.put(requestId, request.setStatus("Error creating quiz"), 60, TimeUnit.MINUTES);
            throw e;
        }
        List<QuestionOutputDto> questions;
        try {
            requestMap.put(requestId, request.setStatus("Creating questions"), 60, TimeUnit.MINUTES);
            TimeUnit.SECONDS.sleep(1);
            questions = createQuizInputDto.getQuestions()
                                          .stream()
                                          .map(question -> {
                                              requestMap.put(requestId, request.setStatus("Creating question " + question.getQuestionText()), 60, TimeUnit.MINUTES);
                                              try {
                                                  TimeUnit.SECONDS.sleep(1);
                                              } catch (InterruptedException e) {
                                                  throw new RuntimeException(e);
                                              }

                                              QuestionInputDto questionInputDto = new QuestionInputDto(

                                              ).setQuizId(quizOutputDto.getId())
                                               .setQuestionText(question.getQuestionText())
                                               .setChoices(question.getChoices())
                                               .setOrder(question.getOrder())
                                               .setMultipleChoice(question.getMultipleChoice());
                                              return questionClient.create(questionInputDto)
                                                                   .getData();
                                          })
                                          .toList();
        } catch (Exception e) {
            requestMap.put(requestId, request.setStatus("Error creating questions"), 60, TimeUnit.MINUTES);
            throw e;
        }
        requestMap.put(requestId, request.setStatus("Creating candidates"), 60, TimeUnit.MINUTES);
        TimeUnit.SECONDS.sleep(1);
        List<UserOutputDto> users = createQuizInputDto.getUsers()
                                                      .stream()
                                                      .map(user -> {
                                                          try {
                                                              requestMap.put(requestId, request.setStatus("Verify candidate: " + user), 60, TimeUnit.MINUTES);
                                                              UserOutputDto userOutputDto = userClient.getUser(user)
                                                                                                      .getData();
                                                              CandidateEntity candidateEntity = new CandidateEntity().setUsername(userOutputDto.getUsername())
                                                                                                                     .setQuizId(quizOutputDto.getId());
                                                              candidateRepository.save(candidateEntity);
                                                              kafkaTemplate.send("notification", new NotificationInputDto().setTopic(userOutputDto.getChatId())
                                                                                                                           .setMessage("Xin chào " + userOutputDto.getFullName() + ". Bạn có bài kiểm tra " + quizOutputDto.getName() + " " + (configOutputDto.getDuration() / 60000) + " phút " + " của " + authorHeader.getUsername() + ". Bài kiểm tra sẽ bắt đầu lúc " + configOutputDto.getStartTime() + " tại " + feHost + "/joinQuiz/" + quizOutputDto.getId() + ". Good luck!"));
                                                              return userOutputDto;
                                                          } catch (
                                                                  Exception e) {
                                                              requestMap.put(requestId, request.setStatus("Candidate not found: " + user), 60, TimeUnit.MINUTES);
                                                              throw e;
                                                          }
                                                      })
                                                      .toList();
        requestMap.put(requestId, request.setStatus("Quiz created"), 60, TimeUnit.MINUTES);
        TimeUnit.SECONDS.sleep(1);
        CreateQuizOutputDto createQuizOutputDto = new CreateQuizOutputDto();
        createQuizOutputDto.setId(quizOutputDto.getId());
        createQuizOutputDto.setName(quizOutputDto.getName());
        createQuizOutputDto.setDescription(quizOutputDto.getDescription());
        createQuizOutputDto.setConfig(configOutputDto);
        createQuizOutputDto.setQuestions(questions);
        return createQuizOutputDto.setUsers(users);
    }

    @Override
    public String status(String requestId, AuthorHeader authorHeader) {
        if (!requestMap.containsKey(requestId)) return "Request not found";
        Request request = (Request) requestMap.get(requestId);
        if (request.getUsername()
                   .equals(authorHeader.getUsername())) {
            return request.getStatus();
        } else {
            throw new ForbiddenException("You are not allowed to see this request status");
        }
    }
}
