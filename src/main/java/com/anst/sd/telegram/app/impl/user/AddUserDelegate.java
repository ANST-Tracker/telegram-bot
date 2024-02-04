package com.anst.sd.telegram.app.impl.user;

import com.anst.sd.telegram.app.api.user.AddUserInBound;
import com.anst.sd.telegram.app.api.user.UserRepository;
import com.anst.sd.telegram.domain.user.UserCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class AddUserDelegate implements AddUserInBound {
    private final UserRepository userRepository;
    private final CreateUserDelegate createUserDelegate;

    @Override
    public void addUser(String telegramId) {
        if (!userRepository.existsByTelegramId(telegramId)) {
            UserCode user = createUserDelegate.createUser(telegramId);
            userRepository.save(user);
        }
    }
}
