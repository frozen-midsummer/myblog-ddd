package com.wjx.myblog.user.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserInfoUpdateCmd {
    private String id;
    private String username;
    private String sex;
    private LocalDateTime birthday;
    private String location;
    private String skills;
    private String feelings;
    private String description;
}
