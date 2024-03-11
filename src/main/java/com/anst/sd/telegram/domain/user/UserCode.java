package com.anst.sd.telegram.domain.user;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
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
    @NotBlank
    private Long chatId;
}