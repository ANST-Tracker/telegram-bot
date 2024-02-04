package com.anst.sd.telegram.app.impl.user;

import com.anst.sd.telegram.app.api.user.CreateUserInBound;
import com.anst.sd.telegram.domain.user.UserCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class CreateUserDelegate implements CreateUserInBound {
    @Override
    public UserCode createUser(String telegramId) {
        return UserCode.builder()
                .code("test_code")
                .telegramId(telegramId)
                .userId("test_userId")
                .build();
    }
}
