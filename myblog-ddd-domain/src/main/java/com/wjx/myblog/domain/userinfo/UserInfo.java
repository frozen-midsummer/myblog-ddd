package com.wjx.myblog.domain.userinfo;

import com.wjx.common.domain.AggregateRoot;
import com.wjx.myblog.domain.userinfo.entity.UserTask;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class UserInfo extends AggregateRoot<Long> {
    private String username;
    private String password;
    private String sex;
    private LocalDateTime birthday;
    private String location;
    private String skills;
    private String feelings;
    private String description;

    private List<UserTask> userTasks;

    public void raiseTask(UserTask userTask) {
        userTask.setArInfo(this);
        allTasks().add(userTask);
    }

    public void clearTasks() {
        this.userTasks = null;
    }

    public List<UserTask> allTasks() {
        if (userTasks == null) {
            this.userTasks = new ArrayList<>();
        }
        return userTasks;
    }

}
