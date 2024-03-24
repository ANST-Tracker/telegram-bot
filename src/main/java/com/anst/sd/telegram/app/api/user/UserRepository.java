package com.anst.sd.telegram.app.api.user;

import com.anst.sd.telegram.domain.user.UserCode;

import java.util.Optional;

public interface UserRepository {
    UserCode save(UserCode userCode);

    Optional<UserCode> findByTelegramId(String telegramId);
}