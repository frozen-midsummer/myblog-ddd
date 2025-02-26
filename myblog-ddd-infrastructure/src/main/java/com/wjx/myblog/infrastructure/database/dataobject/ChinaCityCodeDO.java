package com.wjx.myblog.infrastructure.database.dataobject;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("china_city_code")
public class ChinaCityCodeDO {
    private String name;
    @TableId
    private Integer adCode;
    private Integer cityCode;
}
