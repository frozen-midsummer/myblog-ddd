package com.wjx.myblog.infrastructure.database.convertor;

import com.wjx.myblog.client.dto.UserTaskDTO;
import com.wjx.myblog.infrastructure.database.dataobject.UserTaskDO;
import org.mapstruct.Mapper;

import java.util.List;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface UserTaskConvertor {
    UserTaskDTO toDataTransferObj(UserTaskDO userTaskDO);

    List<UserTaskDTO> toDataTransferObjList(List<UserTaskDO> userTaskDOList);

    UserTaskDO toDataObject(UserTaskDTO userTaskDTO);
}
