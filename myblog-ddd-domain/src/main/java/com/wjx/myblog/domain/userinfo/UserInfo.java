package com.wjx.myblog.domain.userinfo;

import com.wjx.common.domain.AggregateRoot;
import com.wjx.myblog.domain.userinfo.entity.UserTask;
import com.wjx.myblog.domain.userinfo.param.UpdateParamObj;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class UserInfo extends AggregateRoot<Long> {
    private String username;
    private String password;
    private String sex;
    private LocalDate birthday;
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

    public UserTask raiseTask(LocalDateTime deadline, String desc, String alarm) {
        UserTask userTask = new UserTask(deadline, desc, alarm);
        userTask.setArInfo(this);
        allTasks().add(userTask);
        return userTask;
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
