package com.wjx.myblog.infrastructure.database.dataobject;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author MybatisPlusGenerator
 * @since 2025-03-06
 */
@Getter
@Setter
@TableName("myblog_user_task")
public class UserTaskDO implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("task_id")
    private Long taskId;

    private Long userId;

    private LocalDateTime createdTime;

    private LocalDateTime updatedTime;

    private LocalDateTime deadline;

    private String description;

    private String alarm;
}
