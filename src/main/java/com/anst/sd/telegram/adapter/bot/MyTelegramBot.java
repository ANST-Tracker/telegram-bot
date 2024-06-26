package com.anst.sd.telegram.adapter.bot;

import com.anst.sd.telegram.app.api.bot.SendTelegramMessageOutBound;
import com.anst.sd.telegram.app.api.command.ProcessCommandInBound;
import com.anst.sd.telegram.domain.command.ECommand;
import com.anst.sd.telegram.domain.command.MessagePool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScopeDefault;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class MyTelegramBot extends TelegramLongPollingBot implements SendTelegramMessageOutBound {
    private final ProcessCommandInBound processCommandInBound;
    @Value("${tg.bot.token}")
    private String token;
    @Value("${tg.bot.name}")
    private String username;

    public MyTelegramBot(ProcessCommandInBound processCommandInBound) {
        this.processCommandInBound = processCommandInBound;
    }

    @Override
    public void onUpdateReceived(Update update) {
        long messageChatId = update.getMessage().getChatId();
        String telegramId = update.getMessage().getFrom().getUserName();
        String messageText = update.getMessage().getText();

        if (update.hasMessage() && update.getMessage().hasText()) {
            var result = processCommandInBound.processCommand(telegramId, messageText, messageChatId);
            sendMessage(messageChatId, result);
        }
    }

    @Override
    public String getBotUsername() {
        return username;
    }

    @Override
    public String getBotToken() {
        return token;
    }

    public void initCommands() {
        try {
            List<BotCommand> listOfCommands = new ArrayList<>();
            listOfCommands.add(new BotCommand(ECommand.CODE.getCommand(), MessagePool.GET_CODE_COMMAND));
            execute(new SetMyCommands(listOfCommands, new BotCommandScopeDefault(), null));
        } catch (TelegramApiException E) {
            log.warn("Initialization of commands processing failed");
        }
    }

    @Override
    public void sendMessage(long chatId, String text) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(text);
        try {
            execute(message);
        } catch (TelegramApiException e) {
            log.error("Initialization of message processing failed");
        }
    }
}