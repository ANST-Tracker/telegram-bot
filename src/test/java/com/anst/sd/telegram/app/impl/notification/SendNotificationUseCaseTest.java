package com.anst.sd.telegram.app.impl.notification;

import com.anst.sd.telegram.AbstractUnitTest;
import com.anst.sd.telegram.app.api.bot.SendTelegramMessageOutBound;
import com.anst.sd.telegram.app.api.notification.NotificationData;
import com.anst.sd.telegram.app.api.user.UserRepository;
import com.anst.sd.telegram.domain.user.UserCode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static com.anst.sd.telegram.domain.command.MessagePool.NOTIFICATION_MESSAGE;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class SendNotificationUseCaseTest extends AbstractUnitTest {
    private static final LocalDateTime DEADLINE = LocalDateTime.now().plusDays(5);
    private static final String formattedDeadline = DEADLINE.format(DateTimeFormatter.ofPattern("HH:mm"));
    @Mock
    private UserRepository userRepository;
    @Mock
    private SendTelegramMessageOutBound sendTelegramMessageOutBound;
    private SendNotificationUseCase useCase;

    @BeforeEach
    void setUp() {
        useCase = new SendNotificationUseCase(sendTelegramMessageOutBound, userRepository);
    }

    @Test
    void sendNotification_successfully() {
        UserCode userCode = createUserCode();
        NotificationData data = createNotificationData();
        data.setTelegramId(userCode.getTelegramId());
        when(userRepository.findByTelegramId(userCode.getTelegramId())).thenReturn(userCode);

        useCase.sendNotification(data);

        String expectedMessage = NOTIFICATION_MESSAGE
                .formatted(data.getTaskName(), data.getProjectName(), formattedDeadline);
        verify(sendTelegramMessageOutBound).sendMessage(userCode.getChatId(), expectedMessage);
        assertNotNull(expectedMessage);
    }

    private NotificationData createNotificationData() {
        NotificationData notificationData = new NotificationData();
        notificationData.setTaskName("testTaskName");
        notificationData.setProjectName("Routine");
        notificationData.setDeadline(DEADLINE);
        return notificationData;
    }
}