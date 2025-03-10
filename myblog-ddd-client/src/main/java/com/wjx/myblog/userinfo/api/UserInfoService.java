package com.wjx.myblog.userinfo.api;

import com.wjx.common.result.result.ApiResult;
import com.wjx.myblog.userinfo.dto.LoginCmd;
import com.wjx.myblog.userinfo.dto.RegisterCmd;
import com.wjx.myblog.userinfo.dto.UserInfoDTO;
import com.wjx.myblog.userinfo.dto.UserInfoUpdateCmd;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;

public interface UserInfoService {

    ApiResult<String> createAuthenticationToken(LoginCmd loginCmd);

    ResponseEntity<ApiResult> validateToken(HttpServletRequest request);

    ResponseEntity<ApiResult<UserInfoDTO>> getUserInfo(HttpServletRequest request);

    ResponseEntity<ApiResult> register(RegisterCmd registerCmd);

    ApiResult<UserInfoDTO> modify(UserInfoUpdateCmd updateCmd);

}
