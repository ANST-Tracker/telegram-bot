package com.anst.sd.telegram.adapter.rest.notification.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class NotificationDataDto {
    @NotBlank
    String telegramId;
    @NotBlank
    String taskName;
    @NotBlank
    String projectName;
    @NotNull
    LocalDateTime deadline;
}