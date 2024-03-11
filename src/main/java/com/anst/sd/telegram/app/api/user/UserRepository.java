package com.anst.sd.telegram.app.api.user;

import com.anst.sd.telegram.domain.user.UserCode;

public interface UserRepository {

    boolean existsByTelegramId(String telegramId);

    UserCode save(UserCode userCode);

    UserCode findByTelegramId(String telegramId);
}