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
    public static final String GET_CODE_COMMAND = "Получить сгенерированный код";
    public static final String CREATE_TASK_FAILED = """
        Я не смог создать задачу😔
        Возможно, ты еще не использовал меня для авторизации в приложении
        """;
    public static final String CREATE_TASK_SUCCESS = """
        ✅Положил новую задачу в корзину
        """;
    public static final String DEFAULT_ERROR = """
        Я пока не умею обрабатывать такую команду
        """;

    private MessagePool() {
    }
}
