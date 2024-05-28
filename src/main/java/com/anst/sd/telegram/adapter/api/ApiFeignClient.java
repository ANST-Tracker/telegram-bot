package com.anst.sd.telegram.adapter.api;

import com.anst.sd.telegram.adapter.api.dto.CreateTaskInternalDto;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Validated
@FeignClient(name = "api", url = "${service.api.api.url}")
public interface ApiFeignClient {
    @PostMapping("/internal/task")
    void createTask(@RequestBody @Valid CreateTaskInternalDto request);
}
