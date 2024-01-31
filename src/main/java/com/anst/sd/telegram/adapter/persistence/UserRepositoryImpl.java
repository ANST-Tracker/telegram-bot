package com.anst.sd.telegram.adapter.persistence;

import com.anst.sd.telegram.app.api.user.UserRepository;
import com.anst.sd.telegram.domain.user.UserCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {
    private final UserMongoRepository userMongoRepository;

    @Override
    public Optional<UserCode> findByTelegramId(Long telegramId) {
        return userMongoRepository.findByTelegramId(telegramId);
    }

    @Override
    public UserCode save(UserCode userCode) {
        return userMongoRepository.save(userCode);
    }
}
