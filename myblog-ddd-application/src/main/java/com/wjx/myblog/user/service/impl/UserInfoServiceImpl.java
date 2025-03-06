package com.wjx.myblog.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.wjx.common.result.result.ApiResult;
import com.wjx.common.rpc.BaseService;
import com.wjx.myblog.infrastructure.database.convertor.UserInfoConvertor;
import com.wjx.myblog.infrastructure.database.dataobject.UserInfoDO;
import com.wjx.myblog.infrastructure.gatewayimpl.UserInfoGatewayImpl;
import com.wjx.myblog.user.api.UserInfoService;
import com.wjx.myblog.user.dto.RegisterCmd;
import com.wjx.myblog.user.dto.UserDTO;
import com.wjx.myblog.user.dto.UserInfoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserInfoServiceImpl extends BaseService implements UserInfoService {
    @Autowired
    private UserInfoGatewayImpl userInfoGateway;

    @Autowired
    private UserInfoConvertor userInfoConvertor;

    @PostMapping("/register")
    public ResponseEntity<ApiResult> register(@RequestBody RegisterCmd registerCmd) {
        userInfoGateway.add(registerCmd);
        LambdaQueryWrapper<UserInfoDO> queryWrapper = Wrappers.lambdaQuery(UserInfoDO.class)
                .eq(UserInfoDO::getUsername, registerCmd.getUsername());
        return ResponseEntity.ok(ok());
    }
}
