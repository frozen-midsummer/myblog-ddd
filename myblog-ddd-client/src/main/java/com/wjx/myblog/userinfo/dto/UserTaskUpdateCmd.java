package com.wjx.myblog.userinfo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserTaskUpdateCmd {
    private String taskId;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime deadline;
    private String description;
    private String alarm;
    private Integer status;
}
