package it;

import com.anst.sd.telegram.app.api.notification.NotificationData;
import com.anst.sd.telegram.app.api.notification.SendNotificationInBound;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

class NotificationControllerTest extends AbstractIntegrationTest {
    private static final String API_URL = "/internal";

    @MockBean
    SendNotificationInBound sendNotificationInBound;

    @Test
    void sendNotification_successfully() throws Exception {
        NotificationData request = readFromFile("/NotificationDataDtoTest/notificationDataDto.json", NotificationData.class);

        mockMvc.perform(MockMvcRequestBuilders
                        .post(API_URL + "/notification")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse();

        verify(sendNotificationInBound, times(1))
                .sendNotification(any(NotificationData.class));
    }
}