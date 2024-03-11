package com.anst.sd.telegram.app.api.bot;

public interface SendMessageByChatIdOutBound {
    void sendMessage(long chatId, String message);
}