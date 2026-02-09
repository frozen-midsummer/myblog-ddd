package com.wjx.myblog.infrastructure.codegen;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.nio.file.Paths;

public class UserInfoCodeGen {
    public static void main(String[] args) {
        String url = System.getenv("SPRING_DATASOURCE_URL");
        String username = System.getenv("SPRING_DATASOURCE_USERNAME");
        String password = System.getenv("SPRING_DATASOURCE_PASSWORD");
        
        if (url == null || username == null || password == null) {
            System.err.println("请在环境变量中设置 SPRING_DATASOURCE_URL, SPRING_DATASOURCE_USERNAME, SPRING_DATASOURCE_PASSWORD");
            return;
        }

        FastAutoGenerator.create(url, username, password)
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
