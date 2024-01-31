package com.anst.sd.telegram.app.impl.bot;

import com.anst.sd.telegram.app.api.bot.BotProcessInBound;
import com.anst.sd.telegram.app.impl.command.ProcessCommandUseCase;
import com.anst.sd.telegram.domain.bot.BotChange;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class BotProcessUseCase implements BotProcessInBound {
    private final ProcessCommandUseCase processCommandUseCase;

    @Override
    public String process(Long userId, String message) {

        BotChange result;
        result = processCommandUseCase.processCommand(userId, message);

        return result.getMessage();
    }
}
