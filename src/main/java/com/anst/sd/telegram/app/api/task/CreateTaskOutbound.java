package com.anst.sd.telegram.app.api.task;

public interface CreateTaskOutbound {
    void create(String telegramId, String name);
}
