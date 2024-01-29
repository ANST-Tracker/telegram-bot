package com.anst.sd.telegram.service;

import com.anst.sd.telegram.model.constant.UserCode;
import com.anst.sd.telegram.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public void addUser(String code, String telegramId, String userId) {
        if (!isExists(telegramId)) {
            UserCode user = createUser(code, telegramId, userId);
            userRepository.save(user);
        }
    }

    public boolean isExists(String telegramId) {
        return userRepository.findByTelegramId(telegramId).isPresent();
    }

    public UserCode createUser(
            String code,
            String telegramId,
            String userId
    ) {
        return UserCode.builder()
                .code(code)
                .telegramId(telegramId)
                .userId(userId)
                .build();
    }
}
