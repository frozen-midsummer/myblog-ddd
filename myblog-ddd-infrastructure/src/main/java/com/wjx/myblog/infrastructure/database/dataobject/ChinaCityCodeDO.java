package com.wjx.myblog.infrastructure.database.dataobject;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
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
@TableName("myblog_china_city_code")
public class ChinaCityCodeDO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;

    @TableId("ad_code")
    private Integer adCode;

    private Integer cityCode;
}
