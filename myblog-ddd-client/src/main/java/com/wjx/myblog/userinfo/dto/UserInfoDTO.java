package com.wjx.myblog.userinfo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.wjx.common.result.dto.DTO;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class UserInfoDTO extends DTO {
    @JsonSerialize(using = com.fasterxml.jackson.databind.ser.std.ToStringSerializer.class)
    private Long id;
    private String username;
    private String sex;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;
    private String location;
    private String skills;
    private String feelings;
    private String description;
    private String token;
}
