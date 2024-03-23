package com.anst.sd.telegram.adapter.persistence;

import com.anst.sd.telegram.app.api.user.UserRepository;
import com.anst.sd.telegram.domain.user.UserCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {
    private final UserMongoRepository userMongoRepository;

    @Override
    public UserCode save(UserCode userCode) {
        return userMongoRepository.save(userCode);
    }

    @Override
    public UserCode findByTelegramId(String telegramId) {
        return userMongoRepository.findByTelegramId(telegramId);
    }
}