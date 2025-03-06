package com.wjx.myblog.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.wjx.common.result.dto.DTO;
import lombok.Data;

@Data
public class ChinaCityCodeDTO extends DTO {
    private String name;
    @JsonSerialize(using = com.fasterxml.jackson.databind.ser.std.ToStringSerializer.class)
    private Integer adCode;
    @JsonSerialize(using = com.fasterxml.jackson.databind.ser.std.ToStringSerializer.class)
    private Integer cityCode;
}
