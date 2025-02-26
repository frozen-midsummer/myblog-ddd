package com.wjx.myblog.client.dto;

import com.wjx.common.dto.DTO;
import lombok.Data;

@Data
public class ValidateTokenQry extends DTO {
    private String username;
}
