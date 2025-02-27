package com.wjx.myblog.client.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.wjx.common.dto.DTO;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

@Data
public class UserTaskDTO extends DTO {
    @JsonSerialize(using = com.fasterxml.jackson.databind.ser.std.ToStringSerializer.class)
    private Long taskId;
    private String username;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;
    private ZonedDateTime deadline;
    private String description;
    private String alarm;
}
