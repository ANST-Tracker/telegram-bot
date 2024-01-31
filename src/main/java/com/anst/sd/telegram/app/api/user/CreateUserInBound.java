package com.anst.sd.telegram.app.api.user;

import com.anst.sd.telegram.domain.user.UserCode;

public interface CreateUserInBound {
    UserCode createUser(String code, Long telegramId, String userId);
}
