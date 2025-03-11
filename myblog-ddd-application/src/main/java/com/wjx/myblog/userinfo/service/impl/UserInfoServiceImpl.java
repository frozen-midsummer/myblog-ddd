package com.wjx.myblog.userinfo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.wjx.common.exception.SystemException;
import com.wjx.common.result.result.ApiResult;
import com.wjx.common.rpc.BaseService;
import com.wjx.myblog.domain.common.MyblogErrorCodeEnum;
import com.wjx.myblog.domain.userinfo.UserInfo;
import com.wjx.myblog.domain.userinfo.UserInfoDomainService;
import com.wjx.myblog.domain.userinfo.param.UpdateParamObj;
import com.wjx.myblog.infrastructure.config.jwt.JwtTokenUtil;
import com.wjx.myblog.infrastructure.database.convertor.UserInfoConvertor;
import com.wjx.myblog.infrastructure.database.dataobject.UserInfoDO;
import com.wjx.myblog.infrastructure.database.mapper.UserInfoMapper;
import com.wjx.myblog.infrastructure.gatewayimpl.UserInfoGatewayImpl;
import com.wjx.myblog.infrastructure.springsecurity.CustomUserDetailsService;
import com.wjx.myblog.userinfo.api.UserInfoService;
import com.wjx.myblog.userinfo.dto.LoginCmd;
import com.wjx.myblog.userinfo.dto.RegisterCmd;
import com.wjx.myblog.userinfo.dto.UserInfoDTO;
import com.wjx.myblog.userinfo.dto.UserInfoUpdateCmd;
import com.wjx.myblog.userinfo.service.convertor.UserInfoCmdConvertor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/userInfo")
public class UserInfoServiceImpl extends BaseService implements UserInfoService {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private CustomUserDetailsService userDetailsService;
    @Autowired
    private UserInfoGatewayImpl userInfoGateway;
    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private UserInfoCmdConvertor userInfoCmdConvertor;
    @Autowired
    private UserInfoConvertor userInfoConvertor;
    @Autowired
    private UserInfoDomainService userInfoDomainService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    @PostMapping("/login")
    public ApiResult<String> createAuthenticationToken(@RequestBody LoginCmd loginCmd) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginCmd.getUsername(), loginCmd.getPassword()));
        } catch (BadCredentialsException e) {
            throw new SystemException(MyblogErrorCodeEnum.INCORRECT_CREDENTIAL.code(), "Incorrect username or password", e);
        }
        final UserDetails userDetails = userDetailsService.loadUserByUsername(loginCmd.getUsername());
        final String jwt = jwtTokenUtil.generateToken(userDetails);
        return ok(jwt);
    }

    @Override
    @PostMapping("/validateToken")
    public ResponseEntity<ApiResult> validateToken(HttpServletRequest request) {
        try {
            String requestTokenHeader = request.getHeader("Authorization");
            if (requestTokenHeader != null) {
                String jwtToken = requestTokenHeader.substring(7);
                String username = jwtTokenUtil.getUsernameFromToken(jwtToken);
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                if (jwtTokenUtil.validateToken(jwtToken, userDetails)) {
                    return ResponseEntity.ok(ok());
                }
            }
            return ResponseEntity.ok(fail(401, "token 认证失败"));
        } catch (Exception e) {
            return ResponseEntity.ok(fail(e.hashCode(), e.getMessage()));
        }
    }

    @Override
    @PostMapping("/getUserInfo")
    public ResponseEntity<ApiResult<UserInfoDTO>> getUserInfo(HttpServletRequest request) {
        String requestTokenHeader = request.getHeader("Authorization");
        String jwtToken = requestTokenHeader.substring(7);
        String username = jwtTokenUtil.getUsernameFromToken(jwtToken);
        UserInfo userInfo = userInfoGateway.getByUsername(username);
        return ResponseEntity.ok(ok(userInfoConvertor.toUserInfoDTO(userInfo)));
    }

    @Override
    @PostMapping("/register")
    public ResponseEntity<ApiResult> register(@RequestBody RegisterCmd registerCmd) {
        userInfoGateway.add(registerCmd);
        return ResponseEntity.ok(ok());
    }

    @Override
    @PostMapping("/modify")
    public ApiResult<UserInfoDTO> modify(@RequestBody UserInfoUpdateCmd updateCmd) {
        UserInfo userInfo = userInfoGateway.getByUsername(updateCmd.getUsername());
        boolean usernameChanged = userInfo.getUsername().equals(updateCmd.getUsername());
        UpdateParamObj updateParamObj = userInfoCmdConvertor.toUpdateParamObj(updateCmd);
        userInfoDomainService.updateInfo(userInfo, updateParamObj);
        UserInfoDTO res;
        if (usernameChanged) {
            res = userInfoGateway.modify(userInfo);
        } else {
            res = userInfoGateway.modifyUsername(userInfo);
        }
        return ok(res);
    }

}
