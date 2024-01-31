package com.anst.sd.telegram.app.api.bot;

public interface BotProcessInBound {
    String process(Long userId, String message);
}
