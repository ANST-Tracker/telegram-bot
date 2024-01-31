package com.anst.sd.telegram.domain.command;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public enum ECommand {
    CODE("/get_code");

    public static final Map<String, ECommand> stringCommands = Arrays.stream(ECommand.values())
            .collect(Collectors.toMap(ECommand::getCommand, o -> o));
    public static final Set<String> commands = Set.of(
            CODE.getCommand()
    );

    private final String command;

    ECommand(String command) {
        this.command = command;
    }

    public String getCommand() {
        return command;
    }
}
