package com.wjx.myblog.client.dto;

import lombok.Data;

import java.time.ZonedDateTime;

@Data
public class UserTaskCreateCmd {
    private ZonedDateTime deadline;
    private String description;
    private String alarm;
}
