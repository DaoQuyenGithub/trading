package com.develop.config.feign;

import feign.Client;
import feign.Feign;
import feign.Request;
import feign.httpclient.ApacheHttpClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.ssl.TrustStrategy;
import org.springframework.stereotype.Component;

import java.security.cert.X509Certificate;

@Slf4j
@Component
@RequiredArgsConstructor
public class FeignBuilderFactory {
    private final CoreConfig coreConfig;

    public Feign.Builder createFeignBuilder() {
        Feign.Builder builder = null;
        try {
            Client client = this.createHttpClient();

            builder = Feign.builder().client(client)
                    .options(new Request.Options(
                            coreConfig.getFeignConnectTimeoutMillis(),
                            coreConfig.getFeignReadTimeoutMillis()
                    ));

        } catch (Exception e) {
            log.error("Could not create Feign client", e);
        }
        return builder;
    }

    private Client createHttpClient() throws Exception {
        Client httpClient = null;
        HttpClientBuilder builder = HttpClientBuilder.create()
                .setMaxConnPerRoute(coreConfig.getFeignMaxConnectionPerRoute())
                .setMaxConnTotal(coreConfig.getFeignMaxConnectionTotal());
        log.info("feign max connection: {} and feign max connection per route: {}",
                coreConfig.getFeignMaxConnectionTotal(),
                coreConfig.getFeignMaxConnectionPerRoute());

        if(coreConfig.isDisableSslVerification()) {
            log.warn("SSL Verification is disabled");
            builder.setSSLSocketFactory(
                            new SSLConnectionSocketFactory(
                                    SSLContexts.custom().loadTrustMaterial(
                                            null,
                                            new TrustStrategy() {
                                                @Override
                                                public boolean isTrusted(X509Certificate[] x509Certificates, String s) {
                                                    return true;
                                                }
                                            }).build()
                            )
                    )
                    .setSSLHostnameVerifier(NoopHostnameVerifier.INSTANCE);
            httpClient = new ApacheHttpClient(builder.build());
        } else {
            httpClient = new ApacheHttpClient(builder.build());
        }
        return httpClient;
    }
}
