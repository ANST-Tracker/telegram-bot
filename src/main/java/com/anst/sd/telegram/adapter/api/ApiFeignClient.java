package com.anst.sd.telegram.adapter.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "api", url = "${service.api.api.url}")
public interface ApiFeignClient {
    @PostMapping("/internal/task/{userTelegramId}/{name}")
    void createTask(@PathVariable String userTelegramId, @PathVariable String name);
}
