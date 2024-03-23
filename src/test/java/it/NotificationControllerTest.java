//package it;
//
//import com.anst.sd.telegram.adapter.rest.notification.dto.NotificationDataDto;
//import org.junit.jupiter.api.Test;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//
//class NotificationControllerTest extends AbstractIntegrationTest {
//    private static final String API_URL = "/internal";
//
//    @Test
//    void sendNotification__successfully() throws Exception {
//        NotificationDataDto dto = readFromFile("/NotificationDataDtoTest/notificationDataDto.json", NotificationDataDto.class);
//
//        mockMvc.perform(MockMvcRequestBuilders
//                        .post(API_URL + "/notification")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(dto)))
//                .andDo(print())
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andReturn();
//    }
//}