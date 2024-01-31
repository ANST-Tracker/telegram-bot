package com.anst.sd.telegram.app.impl.command;

import com.anst.sd.telegram.app.api.command.ProcessCommandInBound;
import com.anst.sd.telegram.app.impl.user.AddUserUseCase;
import com.anst.sd.telegram.domain.bot.BotChange;
import com.anst.sd.telegram.domain.command.ECommand;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ProcessCommandUseCase implements ProcessCommandInBound {
    private final AddUserUseCase addUserUseCase;
    private final GetCodeUseCase getCodeUseCase;

    @Override
    public BotChange processCommand(Long telegramId, String message) {
        var command = ECommand.stringCommands.get(message);

        BotChange result = null;
        switch (command) {
            case CODE -> {
                String apiCode = "test_code";
                String apiUserId = "test_userId";
                addUserUseCase.addUser(apiCode, telegramId, apiUserId);
                result = getCodeUseCase.handleGetCode(telegramId);
            }
        }
        return result;
    }
}
