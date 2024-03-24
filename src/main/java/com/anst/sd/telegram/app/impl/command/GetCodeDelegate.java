package com.anst.sd.telegram.app.impl.command;

import com.anst.sd.telegram.app.api.user.UserRepository;
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
public class GetCodeDelegate {
    private final UserRepository userRepository;

    @Transactional
    public String handleGetCode(String telegramId, long messageChatId) {
        log.info("The /get_code command was called by user with telegramId {}", telegramId);
        Optional<UserCode> userCode = userRepository.findByTelegramId(telegramId);
        if (userCode.isPresent()) {
            userCode.get().setChatId(messageChatId);
            String code = userCode.get().getCode();
            userCode.get().setCode(null);
            userRepository.save(userCode.get());
            return MessagePool.GET_CODE_SUCCESS + code;
        }
        log.error("User with telegramId {} has not been found in database ", telegramId);
        return MessagePool.GET_CODE_EMPTY;
    }
}