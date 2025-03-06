package com.wjx.myblog.user.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RegisterCmd {
    private String username;
    private String password;
    private String sex;
    private LocalDateTime birthday;
    private String location;
    private String skills;
    private String feelings;
    private String description;
}
