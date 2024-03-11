package com.anst.sd.telegram.app.api.user;

import com.anst.sd.telegram.domain.user.UserCode;

public interface CreateUserCodeInBound {
    UserCode create(UserCode userCode);
}