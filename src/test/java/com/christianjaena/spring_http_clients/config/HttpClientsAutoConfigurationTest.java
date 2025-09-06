package com.christianjaena.spring_http_clients.config;

import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

import static org.assertj.core.api.Assertions.assertThat;


class HttpClientsAutoConfigurationTest {

    private final ApplicationContextRunner contextRunner =
            new ApplicationContextRunner()
                    .withUserConfiguration(TestConfig.class);

    @Configuration
    @EnableAutoConfiguration
    static class TestConfig { }

    @Test
    void shouldCreateRestClientBeanWhenNotExisting() {
        this.contextRunner.run(context -> {
            assertThat(context).hasSingleBean(RestClient.class);
            assertThat(context.getBean(RestClient.class)).isNotNull();
        });
    }

    @Test
    void shouldNotCreateRestClientBeanWhenExisting() {
        this.contextRunner
                .withBean(RestClient.class, RestClient::create)
                .run(context -> assertThat(context).hasSingleBean(RestClient.class));
    }
}
