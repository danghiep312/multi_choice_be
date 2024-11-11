package com.example.notification.service;

import com.example.notification.dto.TokenInputDto;
import com.example.notification.dto.notification.NotificationInputDto;
import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Value("${telegram.token}")
    private String telegramToken;

    @Override
    public void sendNotification(NotificationInputDto notificationInputDto) throws IOException {
        String url = "https://api.telegram.org/bot" + telegramToken + "/sendMessage?chat_id=" + notificationInputDto.getTopic() + "&text=" + notificationInputDto.getMessage();
        Request request = new Request.Builder().url(url)
                                               .build();

        Call call = new okhttp3.OkHttpClient().newCall(request);
        Response res = call.execute();
        System.out.println(res);
    }

    @Override
    public void registerToken(String username, TokenInputDto tokenInputDto) {

    }

    @Override
    public void unregisterToken(String username, TokenInputDto tokenInputDto) {

    }
}
