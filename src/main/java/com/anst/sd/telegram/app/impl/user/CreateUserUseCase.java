package com.anst.sd.telegram.app.impl.user;

import com.anst.sd.telegram.app.api.user.CreateUserInBound;
import com.anst.sd.telegram.domain.user.UserCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class CreateUserUseCase implements CreateUserInBound {
    @Override
    public UserCode createUser(String code, Long telegramId, String userId) {
        return UserCode.builder()
                .code(code)
                .telegramId(telegramId)
                .userId(userId)
                .build();
    }
}
