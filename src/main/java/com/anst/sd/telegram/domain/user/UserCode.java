package com.anst.sd.telegram.domain.user;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Data
@Document(collection = "user_code")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserCode {
    @Id
    private String id;
    @NotBlank
    @Indexed(unique = true)
    private Long userId;
    @NotBlank
    @Indexed(unique = true)
    private String telegramId;
    @NotBlank
    private String code;
    private Instant expirationTime;
    @NotBlank
    private Long chatId;
}