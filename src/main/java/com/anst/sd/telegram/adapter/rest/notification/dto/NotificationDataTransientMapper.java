package com.anst.sd.telegram.adapter.rest.notification.dto;

import com.anst.sd.telegram.app.api.notification.NotificationData;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface NotificationDataTransientMapper {
    NotificationData mapToTransient(NotificationDataDto source);
}