package com.anst.sd.telegram.app.impl.command;

import com.anst.sd.telegram.app.api.command.ProcessCommandInBound;
import com.anst.sd.telegram.domain.command.ECommand;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
@RequiredArgsConstructor
public class ProcessCommandUseCase implements ProcessCommandInBound {
    private final GetCodeDelegate getCodeDelegate;

    @Override
    @Transactional
    public String processCommand(String username, String message, long messageChatId) {
        var command = ECommand.stringCommands.get(message);
        String result = "";
        switch (command) {
            case CODE -> result = getCodeDelegate.handleGetCode(username, messageChatId);
        }
        return result;
    }
}