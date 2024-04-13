package com.anst.sd.telegram.app.impl.notification;

import com.anst.sd.telegram.app.api.bot.SendTelegramMessageOutBound;
import com.anst.sd.telegram.app.api.notification.NotificationData;
import com.anst.sd.telegram.app.api.notification.SendNotificationInBound;
import com.anst.sd.telegram.app.api.user.UserNotFoundException;
import com.anst.sd.telegram.app.api.user.UserRepository;
import com.anst.sd.telegram.domain.user.UserCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.format.DateTimeFormatter;
import java.util.Optional;

import static com.anst.sd.telegram.domain.command.MessagePool.NOTIFICATION_MESSAGE;

@Component
@RequiredArgsConstructor
@Slf4j
public class SendNotificationUseCase implements SendNotificationInBound {
    private static final DateTimeFormatter FORMATTED_DEADLINE = DateTimeFormatter.ofPattern("HH:mm");

    private final SendTelegramMessageOutBound sendTelegramMessageOutBound;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public void sendNotification(NotificationData data) {
        log.info("Notification sending with data {}", data);
        String message = createMessage(data);
        Optional<UserCode> userCode = userRepository.findByTelegramId(data.getTelegramId());
        if (userCode.isEmpty()) {
            throw new UserNotFoundException(data.getTelegramId());
        }
        sendTelegramMessageOutBound.sendMessage(userCode.get().getChatId(), message);
    }

    // ===================================================================================================================
    // = Implementation
    // ===================================================================================================================

    private String createMessage(NotificationData data) {
        String deadline = data.getDeadline().format(FORMATTED_DEADLINE);
        return NOTIFICATION_MESSAGE.formatted(data.getTaskName(), data.getProjectName(), deadline);
    }
}