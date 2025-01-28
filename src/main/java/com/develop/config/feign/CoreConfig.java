package com.develop.config.feign;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("core")
@Data
@Slf4j
public class CoreConfig {
    private boolean disableSslVerification;
    private int numRetriesOnUnauthorized;
    private int secsBetweenRetries;
    private int feignMaxConnectionTotal;
    private int feignMaxConnectionPerRoute;
    private int feignConnectTimeoutMillis;
    private int feignReadTimeoutMillis;
}
