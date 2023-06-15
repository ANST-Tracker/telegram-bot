package com.anst.sd.telegram.model.dto.response;

import com.anst.sd.telegram.model.enums.TaskStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TaskInfo {
    String data;
    TaskStatus status;
}
