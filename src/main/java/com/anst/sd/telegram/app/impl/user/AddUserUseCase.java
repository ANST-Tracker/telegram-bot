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
public class AddUserUseCase implements AddUserInBound {
    private final UserRepository userRepository;
    private final CreateUserUseCase createUserUseCase;

    @Override
    public void addUser(String code, Long telegramId, String userId) {
        if (!userRepository.findByTelegramId(telegramId).isPresent()) {
            UserCode user = createUserUseCase.createUser(code, telegramId, userId);
            userRepository.save(user);
        }
    }
}
