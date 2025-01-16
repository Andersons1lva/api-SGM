package com.adb.Sgm.configuration;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class AuthConfig {
    @Value("${api.security.token.secret}")
    private String secret;

    @Value("${jwt.expiresIn:3600000}")
    private long expiresIn;
}
