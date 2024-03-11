package com.anst.sd.telegram.app.api.command;

public interface ProcessCommandInBound {
    String processCommand(String username, String message, long messageChatId);
}