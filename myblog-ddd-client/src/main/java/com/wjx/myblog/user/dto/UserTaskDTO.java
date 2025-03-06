package com.wjx.myblog.user.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.wjx.common.result.dto.DTO;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserTaskDTO extends DTO {
    @JsonSerialize(using = com.fasterxml.jackson.databind.ser.std.ToStringSerializer.class)
    private Long taskId;
    private String username;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;
    private LocalDateTime deadline;
    private String description;
    private String alarm;
}
