package com.wjx.myblog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.wjx.myblog"})
public class SpringbootMyblogApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringbootMyblogApplication.class, args);
    }
}