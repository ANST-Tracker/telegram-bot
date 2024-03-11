package com.anst.sd.telegram.app.impl.notification;

import com.anst.sd.telegram.AbstractUnitTest;
import com.anst.sd.telegram.app.api.bot.SendMessageByChatIdOutBound;
import com.anst.sd.telegram.app.api.notification.NotificationData;
import com.anst.sd.telegram.app.api.user.UserRepository;
import com.anst.sd.telegram.domain.user.UserCode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static com.anst.sd.telegram.domain.command.MessagePool.NOTIFICATION_DEADLINE_WARNING;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class SendNotificationUseCaseTest extends AbstractUnitTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private SendMessageByChatIdOutBound sendMessageByChatIdOutBound;
    @InjectMocks
    private SendNotificationUseCase useCase;
    private static final LocalDateTime DEADLINE = LocalDateTime.now().plusDays(5);

    @BeforeEach
    void setUp() {
        useCase = new SendNotificationUseCase(sendMessageByChatIdOutBound, userRepository);
    }

    @Test
    void sendNotification__successfully() {
        String telegramId = "eridiium";
        long chatId = 12345L;
        UserCode userCode = new UserCode();
        userCode.setChatId(chatId);

        when(userRepository.findByTelegramId(telegramId)).thenReturn(userCode);

        NotificationData data = new NotificationData(telegramId, "Do hw", "Routine", DEADLINE);
        useCase.sendNotification(data);
        String formattedDeadline = DEADLINE.format(DateTimeFormatter.ofPattern("HH:mm"));
        String expectedMessage = NOTIFICATION_DEADLINE_WARNING
                .formatted(data.getTaskName(), data.getProjectName(), formattedDeadline);

        verify(sendMessageByChatIdOutBound).sendMessage(chatId, expectedMessage);
        assertNotNull(expectedMessage);
    }
}