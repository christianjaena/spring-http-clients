package com.christianjaena.spring_http_clients.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class HttpClientsAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public RestClient restClient() {
        return RestClient.create();
    }

}
