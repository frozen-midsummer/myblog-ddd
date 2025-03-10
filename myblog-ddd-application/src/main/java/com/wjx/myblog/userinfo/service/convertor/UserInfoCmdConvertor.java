package com.wjx.myblog.userinfo.service.convertor;

import com.wjx.myblog.domain.userinfo.param.UpdateParamObj;
import com.wjx.myblog.userinfo.dto.UserInfoUpdateCmd;
import org.mapstruct.Mapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING, imports = {})
public interface UserInfoCmdConvertor {
    UpdateParamObj toUpdateParamObj(UserInfoUpdateCmd updateCmd);
}
