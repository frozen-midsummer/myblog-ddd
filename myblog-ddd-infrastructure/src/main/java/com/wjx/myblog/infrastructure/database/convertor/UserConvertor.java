package com.wjx.myblog.infrastructure.database.convertor;

import com.wjx.myblog.client.dto.RegisterCmd;
import com.wjx.myblog.client.dto.UserDTO;
import com.wjx.myblog.client.dto.UserInfoDTO;
import com.wjx.myblog.infrastructure.database.dataobject.UserDO;
import com.wjx.myblog.infrastructure.database.dataobject.UserInfoDO;
import org.mapstruct.*;


import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface UserConvertor {
    UserDTO toDataTransferObj(UserDO userDO);

    UserDO toDataObject(UserDTO userDTO);

    UserInfoDO toUserInfoDO(RegisterCmd registerCmd);

    UserInfoDTO toUserInfoDTO(UserInfoDO userInfoDO);
}
