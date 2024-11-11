package com.example.join_quiz.service;

import com.example.join_quiz.client.*;
import com.example.join_quiz.dto.answer.AnswerInputDto;
import com.example.join_quiz.dto.answer.AnswerOutputDto;
import com.example.join_quiz.dto.choice.ChoiceContainCorrectOutputDto;
import com.example.join_quiz.dto.config.ConfigOutputDto;
import com.example.join_quiz.dto.join_quiz.JoinQuizOutputDto;
import com.example.join_quiz.dto.notification.NotificationInputDto;
import com.example.join_quiz.dto.question.QuestionContainCorrectOutputDto;
import com.example.join_quiz.dto.question.QuestionOutputDto;
import com.example.join_quiz.dto.quiz.QuizOutputDto;
import com.example.join_quiz.dto.submit.SubmitInputDto;
import com.example.join_quiz.dto.submit.SubmitOutputDto;
import com.example.join_quiz.dto.user.UserOutputDto;
import com.example.join_quiz.dto.user_choice.UserChoiceInputDto;
import com.example.join_quiz.entity.Request;
import com.example.join_quiz.exeption.ForbiddenException;
import com.example.join_quiz.exeption.NotFoundException;
import com.example.join_quiz.repository.CandidateRepository;
import org.redisson.api.RMapCache;
import org.redisson.api.RedissonClient;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class JoinQuizServiceImpl implements JoinQuizService {

    private final CandidateRepository candidateRepository;
    private final QuizClient quizClient;
    private final UserClient userClient;
    private final ConfigClient configClient;
    private final QuestionClient questionClient;
    private final AnswerClient answerClient;

    private final RMapCache<Object, Object> requestMap;

    private final KafkaTemplate<String, NotificationInputDto> kafkaTemplate;

    public JoinQuizServiceImpl(CandidateRepository candidateRepository, QuizClient quizClient, UserClient userClient, ConfigClient configClient, QuestionClient questionClient, AnswerClient answerClient, RedissonClient redissonClient, KafkaTemplate<String, NotificationInputDto> kafkaTemplate) {
        this.candidateRepository = candidateRepository;
        this.quizClient = quizClient;
        this.userClient = userClient;
        this.configClient = configClient;
        this.questionClient = questionClient;
        this.answerClient = answerClient;
        this.requestMap = redissonClient.getMapCache("join-quiz");
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public JoinQuizOutputDto joinQuiz(Long quizId, String username, String requestId) throws InterruptedException {
        Request request = new Request().setUsername(username)
                                       .setStatus("Checking user info");
        try {
            requestMap.put(requestId, request, 60, java.util.concurrent.TimeUnit.MINUTES);
            TimeUnit.SECONDS.sleep(1);
            userClient.getUser(username);
        } catch (Exception e) {
            requestMap.put(requestId, request.setStatus("User not found"), 60, TimeUnit.MINUTES);
            throw new ForbiddenException("User not found");
        }
        if (!candidateRepository.existsByQuizIdAndUsername(quizId, username)) {
            requestMap.put(requestId, request.setStatus("User cannot join this quiz"), 60, TimeUnit.MINUTES);
            throw new ForbiddenException("User cannot join this quiz");
        }
        QuizOutputDto quizOutputDto;
        try {
            requestMap.put(requestId, request.setStatus("Checking quiz info"), 60, TimeUnit.MINUTES);
            TimeUnit.SECONDS.sleep(1);
            quizOutputDto = quizClient.findById(quizId)
                                      .getData();
        } catch (Exception e) {
            requestMap.put(requestId, request.setStatus("Quiz not found"), 60, TimeUnit.MINUTES);
            throw new NotFoundException("Quiz not found");
        }

        ConfigOutputDto configOutputDto;

        try {
            requestMap.put(requestId, request.setStatus("Checking config info"), 60, TimeUnit.MINUTES);
            TimeUnit.SECONDS.sleep(1);
            configOutputDto = configClient.findById(quizOutputDto.getConfigId())
                                          .getData();
        } catch (Exception e) {
            requestMap.put(requestId, request.setStatus("Config not found"), 60, TimeUnit.MINUTES);
            throw new NotFoundException("Config not found");
        }
        requestMap.put(requestId, request.setStatus("Checking time"), 60, TimeUnit.MINUTES);
        TimeUnit.SECONDS.sleep(1);
        if (configOutputDto.getStartTime()
                           .isAfter(OffsetDateTime.now())) {
            requestMap.put(requestId, request.setStatus("It's not time to join quiz"), configOutputDto.getDuration() > 60000 ? configOutputDto.getDuration() : 60000, TimeUnit.MILLISECONDS);
            throw new ForbiddenException("It's not time to join quiz");
        } else {
            // verify end time
            OffsetDateTime endTime = configOutputDto.getStartTime()
                                                    .plusSeconds(configOutputDto.getDuration() / 1000);
            if (endTime.isBefore(OffsetDateTime.now())) {
                requestMap.put(requestId, request.setStatus("It's too late to join quiz"), 60, TimeUnit.MINUTES);
                throw new ForbiddenException("It's too late to join quiz");
            } else {
                List<AnswerOutputDto> answers = answerClient.getAnswersByQuizId(username, quizId)
                                                            .getData();
                if (!answers.isEmpty()) {
                    requestMap.put(requestId, request.setStatus("User has already submitted this quiz"), 60, TimeUnit.MINUTES);
                    throw new ForbiddenException("User has already submitted this quiz");
                }
                requestMap.put(requestId, request.setStatus("Getting questions"), 60, TimeUnit.MINUTES);
                TimeUnit.SECONDS.sleep(1);
                List<QuestionOutputDto> questions = questionClient.getQuestionsByQuiz(quizId)
                                                                  .getData();
                JoinQuizOutputDto joinQuizOutputDto = new JoinQuizOutputDto();
                joinQuizOutputDto.setConfig(configOutputDto)
                                 .setQuestions(questions);
                joinQuizOutputDto.setId(quizOutputDto.getId())
                                 .setName(quizOutputDto.getName())
                                 .setDescription(quizOutputDto.getDescription());
                requestMap.put(requestId, request.setStatus("Success"), 60, TimeUnit.MINUTES);
                return joinQuizOutputDto;
            }
        }
    }

    @Override
    public SubmitOutputDto submit(SubmitInputDto submitInputDto, String username, Long quizId, String requestId) throws InterruptedException {
        Request request = new Request().setUsername(username)
                                       .setStatus("Checking user info");
        UserOutputDto userOutputDto;
        try {
            requestMap.put(requestId, request, 60, TimeUnit.MINUTES);
            TimeUnit.SECONDS.sleep(1);
            userOutputDto = userClient.getUser(username)
                                      .getData();
        } catch (Exception e) {
            requestMap.put(requestId, request.setStatus("User not found"), 60, TimeUnit.MINUTES);
            throw new ForbiddenException("User not found");
        }
        if (!candidateRepository.existsByQuizIdAndUsername(quizId, username)) {
            requestMap.put(requestId, request.setStatus("User cannot submit this quiz"), 60, TimeUnit.MINUTES);
            throw new ForbiddenException("User cannot submit this quiz");
        }

        QuizOutputDto quizOutputDto;
        UserOutputDto author;
        try {
            requestMap.put(requestId, request.setStatus("Checking quiz info"), 60, TimeUnit.MINUTES);
            TimeUnit.SECONDS.sleep(1);
            quizOutputDto = quizClient.findById(quizId)
                                      .getData();
            author = userClient.getUser(quizOutputDto.getAuthorName())
                               .getData();
        } catch (Exception e) {
            requestMap.put(requestId, request.setStatus("Quiz not found"), 60, TimeUnit.MINUTES);
            throw new NotFoundException("Quiz not found");
        }
        ConfigOutputDto configOutputDto;
        try {
            requestMap.put(requestId, request.setStatus("Checking config info"), 60, TimeUnit.MINUTES);
            TimeUnit.SECONDS.sleep(1);
            configOutputDto = configClient.findById(quizOutputDto.getConfigId())
                                          .getData();
        } catch (Exception e) {
            requestMap.put(requestId, request.setStatus("Config not found"), 60, TimeUnit.MINUTES);
            throw new NotFoundException("Config not found");
        }
        requestMap.put(requestId, request.setStatus("Checking time"), 60, TimeUnit.MINUTES);
        TimeUnit.SECONDS.sleep(1);
        if (configOutputDto.getStartTime()
                           .isAfter(OffsetDateTime.now())) {
            requestMap.put(requestId, request.setStatus("It's not time to submit quiz"), configOutputDto.getDuration() > 60000 ? configOutputDto.getDuration() : 60000, TimeUnit.MILLISECONDS);
            throw new ForbiddenException("It's not time to submit quiz");
        } else {
            // verify end time
            OffsetDateTime endTime = configOutputDto.getStartTime()
                                                    .plusSeconds(configOutputDto.getDuration() / 1000);
            if (endTime.isBefore(OffsetDateTime.now())) {
                requestMap.put(requestId, request.setStatus("It's too late to submit quiz"), 60, TimeUnit.MINUTES);
                throw new ForbiddenException("It's too late to submit quiz");
            } else {
                List<AnswerOutputDto> answers;
                try {
                    requestMap.put(requestId, request.setStatus("Checking exist user answers"), 60, TimeUnit.MINUTES);
                    TimeUnit.SECONDS.sleep(1);
                    answers = answerClient.getAnswersByQuizId(username, quizId)
                                          .getData();
                } catch (Exception e) {
                    requestMap.put(requestId, request.setStatus("Error getting user answers"), 60, TimeUnit.MINUTES);
                    throw e;
                }
                if (!answers.isEmpty()) {
                    requestMap.put(requestId, request.setStatus("User has already submitted this quiz"), 60, TimeUnit.MINUTES);
                    throw new ForbiddenException("User has already submitted this quiz");
                }
                double totalScore = 10.0;
                List<QuestionContainCorrectOutputDto> questions;
                try {
                    requestMap.put(requestId, request.setStatus("Getting questions"), 60, TimeUnit.MINUTES);
                    TimeUnit.SECONDS.sleep(1);
                    questions = questionClient.getQuestionContainCorrectByQuiz(quizId)
                                              .getData();
                } catch (Exception e) {
                    requestMap.put(requestId, request.setStatus("Error getting questions"), 60, TimeUnit.MINUTES);
                    throw e;
                }
                requestMap.put(requestId, request.setStatus("Checking user choices"), 60, TimeUnit.MINUTES);
                List<UserChoiceInputDto> correctChoices = questions.stream()
                                                                   .collect(ArrayList::new, (list, question) -> {
                                                                       List<ChoiceContainCorrectOutputDto> correctChoicesInQuestion = question.getChoices();
                                                                       correctChoicesInQuestion.forEach(choice -> {
                                                                           if (choice.getCorrect()) {
                                                                               UserChoiceInputDto userChoiceInputDto = new UserChoiceInputDto();
                                                                               userChoiceInputDto.setQuestionId(question.getId())
                                                                                                 .setChoiceId(choice.getId());
                                                                               list.add(userChoiceInputDto);
                                                                           }
                                                                       });
                                                                   }, ArrayList::addAll);
                double unitScore = totalScore / correctChoices.size();
                double userScore = 0.0;
                List<UserChoiceInputDto> userChoices = submitInputDto.getChoices();
                for (UserChoiceInputDto userChoice : userChoices) {
                    if (correctChoices.contains(userChoice)) {
                        userScore += unitScore;
                        answerClient.createAnswer(new AnswerInputDto().setScore(unitScore)
                                                                      .setQuestionId(userChoice.getQuestionId())
                                                                      .setChoiceId(userChoice.getChoiceId()), username, quizId);
                    } else {
                        answerClient.createAnswer(new AnswerInputDto().setScore(0.0)
                                                                      .setQuestionId(userChoice.getQuestionId())
                                                                      .setChoiceId(userChoice.getChoiceId()), username, quizId);
                    }
                }
                requestMap.put(requestId, request.setStatus("Success"), 60, TimeUnit.MINUTES);
                kafkaTemplate.send("notification", new NotificationInputDto().setTopic(author.getChatId())
                                                                             .setMessage(userOutputDto.getFullName() + " đã hoàn thành bài kiểm tra " + quizOutputDto.getName() + " với số điểm " + userScore + "."));
                return new SubmitOutputDto().setScore(userScore)
                                            .setQuestions(questions.stream()
                                                                   .peek(question -> question.getChoices()
                                                                                             .forEach(choice -> {
                                                                                                 choice.setSelected(userChoices.contains(new UserChoiceInputDto().setQuestionId(question.getId())
                                                                                                                                                                 .setChoiceId(choice.getId())));
                                                                                             }))
                                                                   .toList());
            }
        }
    }

    @Override
    public String status(String requestId, String username) {
        if (!requestMap.containsKey(requestId)) return "Request not found";
        Request request = (Request) requestMap.get(requestId);
        if (request.getUsername()
                   .equals(username)) {
            return request.getStatus();
        } else {
            throw new ForbiddenException("You are not allowed to see this request status");
        }
    }
}
