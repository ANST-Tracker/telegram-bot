package com.anst.sd.telegram.app.impl.user;

import com.anst.sd.telegram.app.api.user.UserRepository;
import com.anst.sd.telegram.domain.user.UserCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class SaveUserCodeUseCase {
    private final UserRepository userRepository;

    public UserCode save(UserCode userCode) {
        Optional<UserCode> existedUser = userRepository.findByTelegramId(userCode.getTelegramId());

        if (existedUser.isPresent()) {
            UserCode user = existedUser.get();
            user.setCode(userCode.getCode());
            return userRepository.save(user);
        }

        return userRepository.save(userCode);
    }

}