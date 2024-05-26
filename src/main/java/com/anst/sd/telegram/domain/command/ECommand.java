package com.anst.sd.telegram.domain.command;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public enum ECommand {
    CODE("/get_code");

    private static final Map<String, ECommand> STRING_TO_COMMAND_MAP = Arrays.stream(ECommand.values())
            .collect(Collectors.toMap(ECommand::getCommand, o -> o));

    private final String command;

    ECommand(String command) {
        this.command = command;
    }

    public String getCommand() {
        return command;
    }

    public static boolean isCommand(String message) {
        return STRING_TO_COMMAND_MAP.containsKey(message);
    }

    public static ECommand defineCommand(String message) {
        return STRING_TO_COMMAND_MAP.get(message);
    }
}