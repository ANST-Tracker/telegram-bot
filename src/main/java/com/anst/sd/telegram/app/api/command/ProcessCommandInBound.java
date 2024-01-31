package com.anst.sd.telegram.app.api.command;

import com.anst.sd.telegram.domain.bot.BotChange;

public interface ProcessCommandInBound {
    BotChange processCommand(Long telegramId, String message);
}
