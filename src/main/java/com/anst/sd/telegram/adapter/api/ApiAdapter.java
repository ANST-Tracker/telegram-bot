package com.anst.sd.telegram.adapter.api;

import com.anst.sd.telegram.adapter.api.dto.CreateTaskInternalDto;
import com.anst.sd.telegram.app.api.ServiceUnavailableException;
import com.anst.sd.telegram.app.api.task.CreateTaskOutbound;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class ApiAdapter implements CreateTaskOutbound {
    private final ApiFeignClient apiFeignClient;

    @Override
    public void create(String telegramId, String name) {
        log.info("Sending create task request to api service. user {}, taskName {}", telegramId, name);
        try {
            apiFeignClient.createTask(new CreateTaskInternalDto(telegramId, name));
        } catch (FeignException e) {
            log.error("Error occurred while sending create task request request", e);
            throw new ServiceUnavailableException(e);
        }
    }
}
