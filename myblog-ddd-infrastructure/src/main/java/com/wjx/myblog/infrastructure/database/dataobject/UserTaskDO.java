package com.wjx.myblog.infrastructure.database.dataobject;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.wjx.common.dto.DTO;
import lombok.Data;
import lombok.NonNull;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Date;

@Data
@TableName("usertasks")
public class UserTaskDO {
    @NonNull
    @TableId(type = IdType.AUTO)
    private Long taskId;
    @NonNull
    @TableField("username")
    private String username;
    @NonNull
    @TableField("created_time")
    private LocalDateTime createdTime;
    @NonNull
    @TableField("updated_time")
    private LocalDateTime updatedTime;
    @NonNull
    @TableField("deadline")
    private ZonedDateTime deadline;
    @NonNull
    private String description;
    @NonNull
    private String alarm;
}
