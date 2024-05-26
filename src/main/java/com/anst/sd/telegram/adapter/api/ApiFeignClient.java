package com.anst.sd.telegram.adapter.api;

import com.anst.sd.telegram.adapter.api.dto.CreateTaskInternalDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "api", url = "${service.api.api.url}")
public interface ApiFeignClient {
    @PostMapping("/internal/task")
    void createTask(@RequestBody CreateTaskInternalDto request);
}
