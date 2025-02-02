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

import java.util.Optional;

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
        String message = NOTIFICATION_MESSAGE.formatted(data.getTitle(), data.getBody());
        Optional<UserCode> userCode = userRepository.findByTelegramId(data.getTelegramId());
        if (userCode.isEmpty() || userCode.get().getChatId() == null) {
            throw new UserNotFoundException(data.getTelegramId());
        }
        sendTelegramMessageOutBound.sendMessage(userCode.get().getChatId(), message);
    }
}