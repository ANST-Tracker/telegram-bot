package it;

import com.anst.sd.telegram.app.api.notification.NotificationData;
import com.anst.sd.telegram.domain.user.UserCode;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

class NotificationControllerTest extends AbstractIntegrationTest {
    private static final String API_URL = "/internal";

    @Test
    void sendNotification_successfully() throws Exception {
        NotificationData request = readFromFile("/NotificationDataDtoTest/notificationDataDto.json", NotificationData.class);
        createUserCode();

        mockMvc.perform(MockMvcRequestBuilders
                        .post(API_URL + "/notification")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse();
    }

    // ===================================================================================================================
    // = Implementation
    // ===================================================================================================================

    private void createUserCode() {
        UserCode userCode = new UserCode();
        userCode.setTelegramId("eridiium");
        userCode.setUserId(1L);
        userCode.setChatId(123L);
        userMongoRepository.save(userCode);
    }
}