package com.example.notification.service;

import com.example.notification.dto.TokenInputDto;
import com.example.notification.dto.notification.NotificationInputDto;

import java.io.IOException;

public interface NotificationService {
    void sendNotification(NotificationInputDto notificationInputDto) throws IOException;
    void registerToken(String username, TokenInputDto tokenInputDto);

    void unregisterToken(String username, TokenInputDto tokenInputDto);
}
