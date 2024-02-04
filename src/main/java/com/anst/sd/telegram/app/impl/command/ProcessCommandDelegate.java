package com.anst.sd.telegram.app.impl.command;

import com.anst.sd.telegram.app.api.command.ProcessCommandInBound;
import com.anst.sd.telegram.domain.command.ECommand;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ProcessCommandDelegate implements ProcessCommandInBound {
    private final GetCodeDelegate getCodeDelegate;

    @Override
    public String processCommand(String username, String message) {
        var command = ECommand.stringCommands.get(message);

        String result = "";
        switch (command) {
            case CODE -> result = getCodeDelegate.handleGetCode(username);
            //...
        }
        return result;
    }
}
