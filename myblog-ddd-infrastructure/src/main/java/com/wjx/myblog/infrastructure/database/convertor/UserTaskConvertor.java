package com.wjx.myblog.infrastructure.database.convertor;

import com.wjx.myblog.domain.userinfo.entity.UserTask;
import com.wjx.myblog.userinfo.dto.UserTaskDTO;
import com.wjx.myblog.infrastructure.database.dataobject.UserTaskDO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface UserTaskConvertor {

    @Mapping(target = "taskId", source = "id")
    UserTaskDTO toDataTransferObj(UserTask userTask);

    UserTaskDO convertDataObject(UserTaskDTO userTaskDTO);

    @Mapping(target = "taskId", source = "id")
    @Mapping(target = "userId", source = "arId")
    UserTaskDO toDataObject(UserTask userTask);

    @Mapping(target = "id", source = "taskId")
    @Mapping(target = "arId", source = "userId")
    UserTask toUserTask(UserTaskDO userTaskDO);

    UserTaskDTO convertUserTaskDTO(UserTaskDO userTaskDO);

    List<UserTaskDTO> convertUserTaskDTO(List<UserTaskDO> userTaskDOList);
}
