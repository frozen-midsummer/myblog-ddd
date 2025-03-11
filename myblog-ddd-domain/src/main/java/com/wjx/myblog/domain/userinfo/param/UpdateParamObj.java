package com.wjx.myblog.domain.userinfo.param;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class UpdateParamObj {
    private String id;
    private String username;
    private String sex;
    private LocalDate birthday;
    private String location;
    private String skills;
    private String feelings;
    private String description;
}
