package com.anst.sd.telegram.app.api.user;

import com.anst.sd.telegram.domain.user.UserCode;

public interface UserRepository {
    UserCode save(UserCode userCode);

    UserCode findByTelegramId(String telegramId);
}