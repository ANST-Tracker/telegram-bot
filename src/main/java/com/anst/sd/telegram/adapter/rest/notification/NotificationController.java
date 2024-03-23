package com.anst.sd.telegram.adapter.rest.notification;

import com.anst.sd.telegram.adapter.rest.notification.dto.NotificationDataDto;
import com.anst.sd.telegram.adapter.rest.notification.dto.NotificationDataTransientMapper;
import com.anst.sd.telegram.app.api.notification.NotificationData;
import com.anst.sd.telegram.app.api.notification.SendNotificationInBound;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/internal")
public class NotificationController {
    private final NotificationDataTransientMapper notificationDataTransientMapper;
    private final SendNotificationInBound sendNotificationInBound;

    @PostMapping("/notification")
    public void sendNotification(@Valid @RequestBody NotificationDataDto notificationDataDto) {
        NotificationData data = notificationDataTransientMapper.mapToTransient(notificationDataDto);
        sendNotificationInBound.sendNotification(data);
    }
}