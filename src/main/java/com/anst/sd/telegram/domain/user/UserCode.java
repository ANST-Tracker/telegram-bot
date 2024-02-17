package com.anst.sd.telegram.domain.user;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
}
