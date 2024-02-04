package com.anst.sd.telegram.domain.user;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Getter
@Setter
@Builder
public class UserCode {
    @Id
    private String id;
    @NotBlank
    @Indexed(unique = true)
    private String userId;
    @NotBlank
    @Indexed(unique = true)
    private String telegramId;
    @NotBlank
    private String code;
}
