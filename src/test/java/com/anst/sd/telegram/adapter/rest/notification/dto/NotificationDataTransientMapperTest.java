package com.anst.sd.telegram.adapter.rest.notification.dto;

import com.anst.sd.telegram.AbstractUnitTest;
import com.anst.sd.telegram.app.api.notification.NotificationData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

class NotificationDataTransientMapperTest extends AbstractUnitTest {
    private NotificationDataTransientMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = Mappers.getMapper(NotificationDataTransientMapper.class);
    }

    @Test
    void mapToTransient__notificationData() {
        NotificationDataDto source = readFromFile("/NotificationDataTransientMapperTest/notification.json", NotificationDataDto.class);
        NotificationData entity = mapper.mapToTransient(source);
        assertEqualsToFile("/NotificationDataTransientMapperTest/notificationTransient.json", entity);
    }
}