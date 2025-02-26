package com.wjx.myblog.infrastructure.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.wjx.myblog.infrastructure.database.mapper")
public class MybatisPlusConfig {
}
