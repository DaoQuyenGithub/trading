package com.develop.config.feign;

import com.develop.config.AppConfig;
import com.develop.service.client.IBinanceServiceClient;
import com.develop.service.client.IHoubiServiceClient;
import feign.Feign;
import feign.Logger;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.slf4j.Slf4jLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class FeignConfig {
    private final FeignBuilderFactory feignBuilderFactory;
    private final AppConfig appConfig;

    @Bean
    public IBinanceServiceClient binanceServiceClient() {
        Feign.Builder builder = feignBuilderFactory.createFeignBuilder();
        return builder.encoder(new JacksonEncoder())
                .decoder(new JacksonDecoder())
                .logger(new Slf4jLogger(IBinanceServiceClient.class))
                .logLevel(Logger.Level.FULL)
                .target(IBinanceServiceClient.class, appConfig.getBinanceUrl());
    }

    @Bean
    public IHoubiServiceClient houbiServiceClient() {
        Feign.Builder builder = feignBuilderFactory.createFeignBuilder();
        return builder.encoder(new JacksonEncoder())
                .decoder(new JacksonDecoder())
                .logger(new Slf4jLogger(IHoubiServiceClient.class))
                .logLevel(Logger.Level.FULL)
                .target(IHoubiServiceClient.class, appConfig.getHoubiUrl());
    }
}
