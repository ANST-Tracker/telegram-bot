package com.anst.sd.telegram.adapter.bot;

import com.anst.sd.telegram.adapter.MessageConverter;
import com.anst.sd.telegram.app.api.user.CreateUserCodeInBound;
import com.anst.sd.telegram.domain.user.UserCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Log4j2
@RequiredArgsConstructor
public class UserCodeMessageListener {
    private final MessageConverter<UserCode> messageConverter;
    private final CreateUserCodeInBound createUserCodeInBound;

    @KafkaListener(topics = "TG_SEND_CODE_RQ", groupId = "tgConsumer")
    public void listen(String message) {
        UserCode userCode = messageConverter.deserialize(message, UserCode.class);
        log.info("Object UserCode received: {}", userCode);
        createUserCodeInBound.create(userCode);
    }
}