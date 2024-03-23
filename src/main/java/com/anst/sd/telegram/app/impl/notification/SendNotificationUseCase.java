package com.anst.sd.telegram.app.impl.notification;

import com.anst.sd.telegram.app.api.bot.SendTelegramMessageOutBound;
import com.anst.sd.telegram.app.api.notification.NotificationData;
import com.anst.sd.telegram.app.api.notification.SendNotificationInBound;
import com.anst.sd.telegram.app.api.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.format.DateTimeFormatter;

import static com.anst.sd.telegram.domain.command.MessagePool.NOTIFICATION_MESSAGE;

@Component
@RequiredArgsConstructor
@Slf4j
public class SendNotificationUseCase implements SendNotificationInBound {
    private final SendTelegramMessageOutBound sendTelegramMessageOutBound;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public void sendNotification(NotificationData data) {
        log.info("Notification sending with data {}", data);
        String message = createMessage(data);
        long messageChatId = userRepository.findByTelegramId(data.getTelegramId()).getChatId();
        sendTelegramMessageOutBound.sendMessage(messageChatId, message);
    }

    // ===================================================================================================================
    // = Implementation
    // ===================================================================================================================

    private String createMessage(NotificationData data) {
        String formattedDeadline = data.getDeadline().format(DateTimeFormatter.ofPattern("HH:mm"));
        return NOTIFICATION_MESSAGE
                .formatted(data.getTaskName(), data.getProjectName(), formattedDeadline);
    }
}