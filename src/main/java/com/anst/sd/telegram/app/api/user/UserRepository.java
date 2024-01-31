package com.anst.sd.telegram.app.api.user;

import com.anst.sd.telegram.domain.user.UserCode;

import java.util.Optional;

public interface UserRepository {
    Optional<UserCode> findByTelegramId(Long telegramId);

    UserCode save(UserCode userCode);
}
