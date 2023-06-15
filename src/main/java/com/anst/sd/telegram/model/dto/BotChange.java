package com.anst.sd.telegram.model.dto;

import com.anst.sd.telegram.model.enums.BotState;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BotChange {
    BotState botState;
    String message;
}
