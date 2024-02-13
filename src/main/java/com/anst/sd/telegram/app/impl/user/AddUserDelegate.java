package com.anst.sd.telegram.app.impl.user;

import com.anst.sd.telegram.app.api.user.UserRepository;
import com.anst.sd.telegram.domain.user.UserCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class AddUserDelegate {
    private final UserRepository userRepository;

    public void addUser(String telegramId) {
        if (!userRepository.existsByTelegramId(telegramId)) {
            UserCode user = UserCode.builder()
                    .code("test_code")
                    .userId(1L)
                    .telegramId(telegramId)
                    .build();
            userRepository.save(user);
        }
    }
}
