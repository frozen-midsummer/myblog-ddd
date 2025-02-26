package com.wjx.myblog.infrastructure.database.dataobject;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("users")
public class UserDO {
    @TableId
    private Long id;
    private String username;
    private String password;
}
