package com.wjx.myblog.domain.userinfo;

import com.wjx.common.domain.AggregateRoot;
import com.wjx.myblog.domain.userinfo.entity.UserTask;
import com.wjx.myblog.domain.userinfo.param.UpdateParamObj;
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

    public void updateInfo(UpdateParamObj updateParamObj) {
        this.username = updateParamObj.getUsername();
        this.sex = updateParamObj.getSex();
        this.birthday = updateParamObj.getBirthday();
        this.location = updateParamObj.getLocation();
        this.skills = updateParamObj.getSkills();
        this.feelings = updateParamObj.getFeelings();
        this.description = updateParamObj.getDescription();
    }

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
