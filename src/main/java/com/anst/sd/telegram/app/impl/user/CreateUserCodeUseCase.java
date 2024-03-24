package com.anst.sd.telegram.app.impl.user;

import com.anst.sd.telegram.app.api.user.CreateUserCodeInBound;
import com.anst.sd.telegram.app.api.user.UserRepository;
import com.anst.sd.telegram.domain.user.UserCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class CreateUserCodeUseCase implements CreateUserCodeInBound {
    private final UserRepository userRepository;

    @Override
    @Transactional
    public UserCode create(UserCode userCode) {
        log.info("Starting to create {} from Kafka queue", userCode);
        Optional<UserCode> existedUser = userRepository.findByTelegramId(userCode.getTelegramId());
        if (existedUser.isPresent()) {
            existedUser.get().setCode(userCode.getCode());
            return userRepository.save(existedUser.get());
        }
        return userRepository.save(userCode);
    }
}