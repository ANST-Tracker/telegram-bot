package com.anst.sd.telegram.app.impl.command;

import com.anst.sd.telegram.app.api.command.ProcessCommandInBound;
import com.anst.sd.telegram.domain.command.ECommand;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import static com.anst.sd.telegram.domain.command.MessagePool.DEFAULT_ERROR;

@Slf4j
@Component
@RequiredArgsConstructor
public class ProcessCommandUseCase implements ProcessCommandInBound {
    private final GetCodeDelegate getCodeDelegate;

    @Override
    @Transactional
    public String processCommand(String telegramId, String message, long messageChatId) {
        ECommand command = ECommand.defineCommand(message);
        if (command == ECommand.CODE) {
            return getCodeDelegate.handleGetCode(telegramId, messageChatId);
        }
        return DEFAULT_ERROR;
    }
}