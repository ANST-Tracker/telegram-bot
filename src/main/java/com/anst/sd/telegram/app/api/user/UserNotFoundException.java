package com.anst.sd.telegram.app.api.user;

public class UserNotFoundException extends RuntimeException {
    private static final String ERROR_MESSAGE_TELEGRAMID = "User with telegram id %s was not found";

    public UserNotFoundException(String username) {
        super(ERROR_MESSAGE_TELEGRAMID.formatted(username));
    }
}