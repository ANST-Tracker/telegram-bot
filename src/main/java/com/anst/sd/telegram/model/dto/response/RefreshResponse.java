package com.anst.sd.telegram.model.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RefreshResponse {
    private String accessToken;
    private String refreshToken;
}
