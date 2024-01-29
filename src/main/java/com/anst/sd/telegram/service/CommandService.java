package com.anst.sd.telegram.service;

import com.anst.sd.telegram.model.constant.UserCode;
import com.anst.sd.telegram.repository.UserRepository;
import com.anst.sd.telegram.model.constant.MessagePool;
import com.anst.sd.telegram.model.dto.BotChange;
import com.anst.sd.telegram.model.enums.BotState;
import com.anst.sd.telegram.model.enums.ECommand;
import com.anst.sd.telegram.model.exception.ObjectNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static com.anst.sd.telegram.model.constant.MessagePool.LOGIN_IN_ACCOUNT_WITH_MESSAGE;
import static com.anst.sd.telegram.model.constant.MessagePool.MAIN_MENU;

@Service
@Slf4j
@RequiredArgsConstructor
public class CommandService {
    private final AuthService authService;
    private final TaskService taskService;
    private final UserService userService;
    private final UserRepository userRepository;

    public BotChange processComand(Long userId, String message, BotState botState) {
        var command = ECommand.stringCommands.get(message);

        BotChange result = null;
        switch (command) {
            case LOGIN -> {
                if (botState.equals(BotState.BASE)) {
                    result = processAskUsername();
                }
            }
            case SHOW -> {
                if (botState.equals(BotState.IN_ACCOUNT_BASE)) {
                    result = handleShowState(userId);
                }
            }
            case CREATE -> {
                if (botState.equals(BotState.IN_ACCOUNT_BASE)) {
                    result = processAskTaksData();
                }
            }
            case SIGNOUT -> {
                if (botState.equals(BotState.IN_ACCOUNT_BASE)) {
                    result = authService.signOut(userId, false);
                }
            }
            case START -> result = handleStart(userId);
            case CODE -> {
                String apiCode = "test_code";
                String apiUserId = "test_userId";
                String apiTelegramId = "test_telegramId";
                userService.addUser(apiCode, apiTelegramId, apiUserId);
                result = handleGetCode(apiTelegramId);
                log.info("Code has been received");
            }
        }
        return result;
    }

    private BotChange handleStart(long messageChatId) {
        var result = authService.checkForLogin(messageChatId);
        if (result) {
            return new BotChange(
                    BotState.IN_ACCOUNT_BASE,
                    MessagePool.ALREADY_LOGGED + "\n" + MAIN_MENU);
        } else {
            log.info("User is trying to login");
            return new BotChange(
                    BotState.BASE,
                    LOGIN_IN_ACCOUNT_WITH_MESSAGE);
        }
    }

    private BotChange processAskUsername() {
        return new BotChange(BotState.LOGGING_IN_ASKED_LOGIN, MessagePool.INPUT_USERNAME_MESSAGE);
    }

    public BotChange handleShowState(
            Long messageChatId
    ) {
        var accessToken = authService.getUserAccessToken(messageChatId);
        String response = taskService.getTaskList(accessToken);

        if (response == null) {
            var refreshResult = authService.refreshToken(messageChatId);
            if (!refreshResult) {
                return authService.signOut(messageChatId, true);
            }
            accessToken = authService.getUserAccessToken(messageChatId);
            response = taskService.getTaskList(accessToken);
            log.info("refresh success on get task list");
        }

        if (response.equals("[]")) {
            response = MessagePool.EMPTY_LIST_MESSAGE;
        }

        log.info("User has received a task list");

        return new BotChange(BotState.IN_ACCOUNT_BASE, response + "\n" + MAIN_MENU);
    }

    public BotChange processAskTaksData() {
        return new BotChange(BotState.IN_ACCOUNT_ASKED_TASK, MessagePool.INPUT_TASK_DATA_MESSAGE);
    }

    public BotChange handleGetCode(String telegramId) {
        UserCode userCode = userRepository.findByTelegramId(telegramId)
                .orElseThrow(() -> new ObjectNotFoundException("User code not found with telegram ID: " + telegramId));

        String code = userCode.getCode();
        userCode.setCode(null);
        userRepository.save(userCode);
        return new BotChange(BotState.BASE, " " + MessagePool.GET_CODE_SUCCESS + code);
    }
}
