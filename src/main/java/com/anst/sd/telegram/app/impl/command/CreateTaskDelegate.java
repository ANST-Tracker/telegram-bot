package com.anst.sd.telegram.app.impl.command;

import com.anst.sd.telegram.app.api.task.CreateTaskOutbound;
import com.anst.sd.telegram.app.api.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import static com.anst.sd.telegram.domain.command.MessagePool.CREATE_TASK_FAILED;
import static com.anst.sd.telegram.domain.command.MessagePool.CREATE_TASK_SUCCESS;

@Component
@RequiredArgsConstructor
@Slf4j
public class CreateTaskDelegate {
    private final CreateTaskOutbound createTaskOutbound;
    private final UserRepository userRepository;

    @Transactional
    public String create(String telegramId, String message) {
        log.info("Processing command create task for user {} with message {}", telegramId, message);
        if (!userRepository.existsByTelegramId(telegramId)) {
            return CREATE_TASK_FAILED;
        }
        try {
            createTaskOutbound.create(telegramId, message);
            return CREATE_TASK_SUCCESS;
        } catch (Exception e) {
            return CREATE_TASK_FAILED;
        }
    }
}
