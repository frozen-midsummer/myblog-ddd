package com.wjx.myblog.infrastructure.codegen;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.nio.file.Paths;

public class UserInfoCodeGen {
    public static void main(String[] args) {
        FastAutoGenerator.create("jdbc:mysql://localhost:3306/myblog?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=GMT%2B8&allowPublicKeyRetrieval=true", "root", "208941")
                .globalConfig(builder -> builder
                        .author("MybatisPlusGenerator")
                        .outputDir(Paths.get(System.getProperty("user.dir")) + "/myblog-ddd-infrastructure/target/src/main/java")
                        .commentDate("yyyy-MM-dd")
                )
                .packageConfig(builder -> builder
                        .parent("com.wjx.myblog.infrastructure.database")
                        .entity("dataobject")
                        .mapper("mapper")
                        .service("service")
                        .serviceImpl("service.impl")
                        .xml("mapper.xml")
                )
                .strategyConfig(builder -> builder
                        .addInclude("myblog_user_info")
                        .addInclude("myblog_user_task")
                        .addInclude("myblog_china_city_code")
                        .addTablePrefix("myblog_")
                        .entityBuilder()
                        .enableLombok()
                        .formatFileName("%sDO")
                )
                .templateEngine(new FreemarkerTemplateEngine())
                .execute();
    }
}
