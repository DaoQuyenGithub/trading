package com.develop.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@Data
@Component
@ConfigurationProperties("com.develop.crypto")
public class AppConfig {
    private String binanceUrl;
    private String houbiUrl;
}
