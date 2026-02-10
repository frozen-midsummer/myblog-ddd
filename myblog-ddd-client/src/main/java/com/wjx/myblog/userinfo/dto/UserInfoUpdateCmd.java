package com.wjx.myblog.userinfo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class UserInfoUpdateCmd {
    private String id;
    private String username;
    private String sex;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;
    private String location;
    private String skills;
    private String feelings;
    private String description;
}
