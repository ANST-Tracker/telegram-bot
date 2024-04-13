package com.anst.sd.telegram.domain.command;

public class MessagePool {
    public static final String GET_CODE_SUCCESS = """
            Добро пожаловать!🤗
                        
            Код для подтверждения аккаунта:""";
    public static final String GET_CODE_EMPTY = """
            Код отсутствует.
            Возможно, вы указали неправильный telegramId при регистрации""";
    public static final String NOTIFICATION_MESSAGE = """
            Истекает срок выполнения задачи: "%s"
            В проекте: "%s" в %s
            """;
}