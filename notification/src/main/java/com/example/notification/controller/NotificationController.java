package com.example.notification.controller;

import com.example.notification.base.BaseResponse;
import com.example.notification.dto.TokenInputDto;
import com.example.notification.dto.notification.NotificationInputDto;
import com.example.notification.service.NotificationService;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Component
@KafkaListener(topics = "notification", groupId = "quiz")
public class NotificationController {
    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @KafkaHandler
    public void listenGroupFoo(NotificationInputDto message) throws IOException {
        notificationService.sendNotification(message);
    }

}
