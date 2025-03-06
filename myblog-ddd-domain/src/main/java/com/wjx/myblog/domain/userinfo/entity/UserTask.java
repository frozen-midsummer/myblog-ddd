package com.wjx.myblog.domain.userinfo.entity;

import com.wjx.common.domain.entity.DomainEntity;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

@Data
public class UserTask extends DomainEntity<Long> {
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;
    private LocalDateTime deadline;
    private String description;
    private String alarm;

    public UserTask(LocalDateTime deadline, String desc, String alarm) {
        // 调父类构造方法
        super();
        this.createdTime = LocalDateTime.now();
        this.updatedTime = LocalDateTime.now();
        this.deadline = deadline;
        this.description = desc;
        this.alarm = alarm;
    }

    public void update(LocalDateTime deadline, String description, String alarm) {
        this.updatedTime = LocalDateTime.now();
        this.deadline = deadline;
        this.description = description;
        this.alarm = alarm;
    }
}
