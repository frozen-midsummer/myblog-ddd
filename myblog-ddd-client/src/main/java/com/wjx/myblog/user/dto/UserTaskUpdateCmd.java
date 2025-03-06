package com.wjx.myblog.user.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserTaskUpdateCmd {
    private String taskId;
    private LocalDateTime deadline;
    private String description;
    private String alarm;
}
