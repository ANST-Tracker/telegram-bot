package com.anst.sd.telegram.app.impl.notification;

import com.anst.sd.telegram.AbstractUnitTest;
import com.anst.sd.telegram.app.api.bot.SendTelegramMessageOutBound;
import com.anst.sd.telegram.app.api.notification.NotificationData;
import com.anst.sd.telegram.app.api.user.UserRepository;
import com.anst.sd.telegram.domain.user.UserCode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Optional;

import static com.anst.sd.telegram.domain.command.MessagePool.NOTIFICATION_MESSAGE;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class SendNotificationUseCaseTest extends AbstractUnitTest {
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
        when(userRepository.findByTelegramId(userCode.getTelegramId())).thenReturn(Optional.of(userCode));

        useCase.sendNotification(data);

        String expectedMessage = NOTIFICATION_MESSAGE.formatted(data.getTitle(), data.getBody());
        verify(sendTelegramMessageOutBound).sendMessage(userCode.getChatId(), expectedMessage);
        assertNotNull(expectedMessage);
    }

    // ===================================================================================================================
    // = Implementation
    // ===================================================================================================================

    private NotificationData createNotificationData() {
        NotificationData notificationData = new NotificationData();
        notificationData.setTitle("testTaskName");
        notificationData.setBody("Routine");
        return notificationData;
    }
}