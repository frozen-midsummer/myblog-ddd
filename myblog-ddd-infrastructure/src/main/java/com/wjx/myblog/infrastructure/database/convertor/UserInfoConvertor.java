package com.wjx.myblog.infrastructure.database.convertor;

import com.wjx.myblog.user.dto.RegisterCmd;
import com.wjx.myblog.user.dto.UserDTO;
import com.wjx.myblog.user.dto.UserInfoDTO;
import com.wjx.myblog.domain.userinfo.UserInfo;
import com.wjx.myblog.domain.userinfo.entity.UserTask;
import com.wjx.myblog.infrastructure.database.dataobject.UserInfoDO;
import com.wjx.myblog.infrastructure.database.dataobject.UserTaskDO;
import org.mapstruct.*;
import org.springframework.security.crypto.password.PasswordEncoder;


import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING,imports={PasswordEncoder.class})
public interface UserInfoConvertor {
    UserDTO toUserDTO(UserInfoDO userInfoDO);

    UserInfo toUserInfo(UserInfoDO userInfoDO);

    UserTask toUserTask(UserTaskDO userTaskDO);

    UserInfoDO convertUserInfoDO(RegisterCmd registerCmd);

    UserInfoDO toUserInfoDO(UserInfo userInfo);

    UserInfoDTO convertUserInfoDTO(UserInfoDO userInfoDO);
}
