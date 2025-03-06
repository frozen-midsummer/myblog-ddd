package com.wjx.myblog.infrastructure.gatewayimpl;

import com.wjx.myblog.domain.userinfo.UserInfoGateway;
import com.wjx.myblog.domain.userinfo.UserInfo;
import com.wjx.myblog.infrastructure.database.convertor.UserInfoConvertor;
import com.wjx.myblog.infrastructure.database.dataobject.UserInfoDO;
import com.wjx.myblog.infrastructure.database.mapper.UserInfoMapper;
import com.wjx.myblog.user.dto.RegisterCmd;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserInfoGatewayImpl implements UserInfoGateway {
    @Autowired
    private UserInfoConvertor userInfoConvertor;
    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void add(UserInfo userInfo) {
        UserInfoDO userInfoDO = userInfoConvertor.toUserInfoDO(userInfo);
        userInfoMapper.insert(userInfoDO);
    }

    public void add(RegisterCmd registerCmd) {
        UserInfoDO userInfoDO = userInfoConvertor.convertUserInfoDO(registerCmd);
        userInfoDO.setPassword(passwordEncoder.encode(registerCmd.getPassword()));
        userInfoMapper.insert(userInfoDO);
    }
}
