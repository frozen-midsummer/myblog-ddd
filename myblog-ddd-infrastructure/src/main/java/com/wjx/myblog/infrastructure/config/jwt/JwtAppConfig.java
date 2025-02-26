package com.wjx.myblog.infrastructure.config.jwt;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
@ConfigurationProperties(prefix = "security.jwt")
public class JwtAppConfig {
    public String secretKey;

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }
}
