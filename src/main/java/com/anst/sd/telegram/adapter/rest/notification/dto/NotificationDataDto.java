package com.anst.sd.telegram.adapter.rest.notification.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class NotificationDataDto {
    String telegramId;
    String taskName;
    String projectName;
    LocalDateTime deadline;
}