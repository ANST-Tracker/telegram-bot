package com.anst.sd.telegram.app.impl.command;

import com.anst.sd.telegram.app.api.command.GetCodeInBound;
import com.anst.sd.telegram.app.api.user.UserRepository;
import com.anst.sd.telegram.app.impl.user.AddUserDelegate;
import com.anst.sd.telegram.domain.command.MessagePool;
import com.anst.sd.telegram.domain.user.UserCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class GetCodeDelegate implements GetCodeInBound {
    private final UserRepository userRepository;
    private final AddUserDelegate addUserDelegate;

    @Override
    @Transactional
    public String handleGetCode(String telegramId) {
        log.info("Handling code operation");
        addUserDelegate.addUser(telegramId);

        Optional<UserCode> userCode = userRepository.findByTelegramId(telegramId);

        if (userCode.isPresent()) {
            UserCode user = userCode.get();

            String code = user.getCode();
            user.setCode(null);
            userRepository.save(user);

            log.info("Code has been received");
            return MessagePool.GET_CODE_SUCCESS + code;
        }
        return MessagePool.GET_CODE_EMPTY;
    }
}
