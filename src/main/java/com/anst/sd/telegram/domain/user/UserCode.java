package com.anst.sd.telegram.domain.user;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Getter
@Setter
@Builder
public class UserCode {
    @Id
    private String id;
    private String userId;
    private Long telegramId;
    private String code;
}