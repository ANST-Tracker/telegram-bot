package com.anst.sd.telegram.app.impl.notification;

import com.anst.sd.telegram.app.api.bot.SendMessageByChatIdOutBound;
import com.anst.sd.telegram.app.api.notification.NotificationData;
import com.anst.sd.telegram.app.api.notification.SendNotificationInBound;
import com.anst.sd.telegram.app.api.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.format.DateTimeFormatter;

import static com.anst.sd.telegram.domain.command.MessagePool.NOTIFICATION_DEADLINE_WARNING;

@Component
@RequiredArgsConstructor
@Log4j2
public class SendNotificationUseCase implements SendNotificationInBound {
    private final SendMessageByChatIdOutBound sendMessageByChatIdOutBound;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public void sendNotification(NotificationData data) {
        log.info("Notification sending with data {}", data);
        String message = createMessage(data);
        long messageChatId = userRepository.findByTelegramId(data.getTelegramId()).getChatId();
        sendMessageByChatIdOutBound.sendMessage(messageChatId, message);
    }

    private String createMessage(NotificationData data) {
        String formattedDeadline = data.getDeadline().format(DateTimeFormatter.ofPattern("HH:mm"));
        return NOTIFICATION_DEADLINE_WARNING
                .formatted(data.getTaskName(), data.getProjectName(), formattedDeadline);
    }
}