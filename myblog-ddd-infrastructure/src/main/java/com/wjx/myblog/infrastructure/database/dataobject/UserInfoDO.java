package com.wjx.myblog.infrastructure.database.dataobject;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDate;
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
@TableName("myblog_user_info")
public class UserInfoDO implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.INPUT)
    private Long id;

    private String username;

    private String password;

    private String sex;

    private LocalDate birthday;

    private String location;

    private String skills;

    private String feelings;

    private String description;
}
