package com.wjx.myblog.userinfo.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserTaskCreateCmd {
    private LocalDateTime deadline;
    private String description;
    private String alarm;
}
