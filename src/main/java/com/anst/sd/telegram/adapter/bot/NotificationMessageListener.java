package com.anst.sd.telegram.adapter.bot;

import com.anst.sd.telegram.app.api.notification.NotificationData;
import com.anst.sd.telegram.app.api.notification.SendNotificationInBound;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class NotificationMessageListener {
    private final MessageConverter<NotificationData> messageConverter;
    private final SendNotificationInBound sendNotificationInBound;

    @KafkaListener(topics = "API_NOTIFICATION-CREATED_EVENT", groupId = "tgConsumer")
    public void listen(String message) {
        NotificationData notification = messageConverter.deserialize(message, NotificationData.class);
        log.info("Object notification received: {}", notification);
        sendNotificationInBound.sendNotification(notification);
    }
}