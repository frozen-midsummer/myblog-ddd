package com.wjx.myblog.infrastructure.gatewayimpl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.wjx.common.exception.SystemException;
import com.wjx.common.utils.LongUtil;
import com.wjx.common.utils.SnowflakeIdGenerator;
import com.wjx.myblog.domain.common.MyblogErrorCodeEnum;
import com.wjx.myblog.domain.userinfo.UserInfoGateway;
import com.wjx.myblog.domain.userinfo.UserInfo;
import com.wjx.myblog.domain.userinfo.entity.UserTask;
import com.wjx.myblog.infrastructure.database.convertor.UserInfoConvertor;
import com.wjx.myblog.infrastructure.database.convertor.UserTaskConvertor;
import com.wjx.myblog.infrastructure.database.dataobject.UserInfoDO;
import com.wjx.myblog.infrastructure.database.dataobject.UserTaskDO;
import com.wjx.myblog.infrastructure.database.mapper.UserInfoMapper;
import com.wjx.myblog.infrastructure.database.mapper.UserTaskMapper;
import com.wjx.myblog.userinfo.dto.RegisterCmd;
import com.wjx.myblog.userinfo.dto.UserInfoDTO;
import com.wjx.myblog.userinfo.dto.UserTaskDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserInfoGatewayImpl implements UserInfoGateway {
    @Autowired
    private UserInfoConvertor userInfoConvertor;

    @Autowired
    private UserTaskConvertor userTaskConvertor;
    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private UserTaskMapper userTaskMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserInfo getById(Long id) {
        UserInfoDO userInfoDO = userInfoMapper.selectById(id);
        return userInfoConvertor.toUserInfo(userInfoDO);
    }

    public UserInfo getByUsername(String username) {
        LambdaQueryWrapper<UserInfoDO> queryWrapper = Wrappers.lambdaQuery(UserInfoDO.class)
                .eq(UserInfoDO::getUsername, username);
        UserInfoDO userInfoDO = userInfoMapper.selectOne(queryWrapper);
        return userInfoConvertor.toUserInfo(userInfoDO);
    }

    @Override
    public void add(UserInfo userInfo) {
        if (isUsernameExist(userInfo.getUsername())) {
            throw new SystemException(MyblogErrorCodeEnum.RECORD_DUPLICATE.code(), "用户名已存在，请尝试其他用户名！");
        }
        UserInfoDO userInfoDO = userInfoConvertor.toUserInfoDO(userInfo);
        if (userInfoDO.getId() == null) {
            userInfoDO.setId(SnowflakeIdGenerator.newSnowflakeId());
        }
        userInfoMapper.insert(userInfoDO);
    }

    public void add(RegisterCmd registerCmd) {
        if (isUsernameExist(registerCmd.getUsername())) {
            throw new SystemException(MyblogErrorCodeEnum.RECORD_DUPLICATE.code(), "用户名已存在，请尝试其他用户名！");
        }
        UserInfoDO userInfoDO = userInfoConvertor.convertUserInfoDO(registerCmd);
        userInfoDO.setId(SnowflakeIdGenerator.newSnowflakeId());
        userInfoDO.setPassword(passwordEncoder.encode(registerCmd.getPassword()));
        userInfoMapper.insert(userInfoDO);
    }

    public UserInfoDTO modifyUsername(UserInfo userInfo) {
        if (!userInfoMapper.exists(Wrappers.lambdaQuery(UserInfoDO.class).eq(UserInfoDO::getId, userInfo.getId()))) {
            throw new SystemException(MyblogErrorCodeEnum.RECORD_NOT_FOUND.code(), "用户不存在！");
        }
        // 检查新用户名是否被其他用户占用
        LambdaQueryWrapper<UserInfoDO> queryWrapper = Wrappers.lambdaQuery(UserInfoDO.class)
                .eq(UserInfoDO::getUsername, userInfo.getUsername())
                .ne(UserInfoDO::getId, userInfo.getId());
        if (userInfoMapper.exists(queryWrapper)) {
            throw new SystemException(MyblogErrorCodeEnum.RECORD_DUPLICATE.code(), "用户名已存在，请尝试其他用户名！");
        }
        UserInfoDO userInfoDO = userInfoConvertor.toUserInfoDO(userInfo);
        userInfoMapper.updateById(userInfoDO);
        return userInfoConvertor.convertUserInfoDTO(userInfoDO);
    }

    public UserInfoDTO modify(UserInfo userInfo) {
        if (!userInfoMapper.exists(Wrappers.lambdaQuery(UserInfoDO.class).eq(UserInfoDO::getId, userInfo.getId()))) {
            throw new SystemException(MyblogErrorCodeEnum.RECORD_NOT_FOUND.code(), "用户不存在！");
        }
        UserInfoDO userInfoDO = userInfoConvertor.toUserInfoDO(userInfo);
        userInfoMapper.updateById(userInfoDO);
        return userInfoConvertor.convertUserInfoDTO(userInfoDO);
    }

    public UserTask getTaskById(String id) {
        UserTaskDO userTaskDO = userTaskMapper.selectById(LongUtil.convertStr2Long(id));
        return userTaskConvertor.toUserTask(userTaskDO);
    }

    public List<UserTaskDTO> getTasksByUserId(Long userId) {
        LambdaQueryWrapper<UserTaskDO> userTaskWrapper = Wrappers.lambdaQuery(UserTaskDO.class)
                .eq(UserTaskDO::getUserId, userId);
        List<UserTaskDO> userTaskDOList = userTaskMapper.selectList(userTaskWrapper);
        return userTaskConvertor.convertUserTaskDTO(userTaskDOList);
    }

    public boolean isUsernameExist(String username) {
        return userInfoMapper.exists(Wrappers.lambdaQuery(UserInfoDO.class).eq(UserInfoDO::getUsername, username));
    }
}
