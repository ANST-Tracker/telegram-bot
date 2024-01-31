package com.anst.sd.telegram.app.impl.command;

import com.anst.sd.telegram.app.api.command.GetCodeInBound;
import com.anst.sd.telegram.app.api.user.UserRepository;
import com.anst.sd.telegram.domain.bot.BotChange;
import com.anst.sd.telegram.domain.command.MessagePool;
import com.anst.sd.telegram.domain.user.UserCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class GetCodeUseCase implements GetCodeInBound {
    private final UserRepository userRepository;

    @Override
    public BotChange handleGetCode(Long telegramId) {
        Optional<UserCode> userCode = userRepository.findByTelegramId(telegramId);

        if (userCode.isPresent()) {
            UserCode user = userCode.get();

            String code = user.getCode();
            user.setCode(null);
            userRepository.save(user);

            log.info("Code has been received");
            return new BotChange(" " + MessagePool.GET_CODE_SUCCESS + code);
        }
        return new BotChange(MessagePool.GET_CODE_EMPTY);
    }
}
