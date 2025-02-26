package com.wjx.myblog.infrastructure.database.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wjx.myblog.infrastructure.database.dataobject.UserTaskDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

public interface UserTasksMapper extends BaseMapper<UserTaskDO> {
}
