package com.anst.sd.telegram.app.api.bot;

public interface SendTelegramMessageOutBound {
    void sendMessage(long chatId, String message);
}