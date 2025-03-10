package com.wjx.myblog.domain.userinfo;

import com.wjx.myblog.domain.userinfo.entity.UserTask;
import com.wjx.myblog.domain.userinfo.param.UpdateParamObj;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserInfoDomainService {
    public UserTask addTask(LocalDateTime deadline, String desc, String alarm) {
        return new UserTask(deadline, desc, alarm);
    }

    public void updateTask(UserTask userTask, LocalDateTime deadline, String desc, String alarm) {
        userTask.update(deadline, desc, alarm);
    }

    public void updateInfo(UserInfo userInfo, UpdateParamObj updateParamObj) {
        userInfo.updateInfo(updateParamObj);
    }
}
