package com.anst.sd.telegram.app.impl.command;

import com.anst.sd.telegram.app.api.user.UserRepository;
import com.anst.sd.telegram.domain.user.UserCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.anst.sd.telegram.domain.command.MessagePool.GET_CODE_EMPTY;
import static com.anst.sd.telegram.domain.command.MessagePool.GET_CODE_SUCCESS;

@Slf4j
@Component
@RequiredArgsConstructor
public class GetCodeDelegate {
    private final UserRepository userRepository;

    @Transactional
    public String handleGetCode(String telegramId, long messageChatId) {
        log.info("The /get_code command was called by user with telegramId {}", telegramId);
        Optional<UserCode> optionalUserCode = userRepository.findByTelegramId(telegramId);
        if (optionalUserCode.isPresent()) {
            UserCode userCode = optionalUserCode.get();
            String code = userCode.getCode();
            userCode.setCode(null);
            userCode.setChatId(messageChatId);
            userRepository.save(userCode);
            return GET_CODE_SUCCESS + code;
        }
        log.error("User with telegramId {} has not been found in database ", telegramId);
        return GET_CODE_EMPTY;
    }
}