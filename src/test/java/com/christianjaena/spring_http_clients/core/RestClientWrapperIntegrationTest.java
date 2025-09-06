package com.christianjaena.spring_http_clients.core;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestClient;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class RestClientWrapperIntegrationTest {

    @Mock
    private MockWebServer mockWebServer;

    @InjectMocks
    private RestClientWrapper restClientWrapper;

    @BeforeEach
    void setUp() throws IOException {
        mockWebServer = new MockWebServer();
        mockWebServer.start();

        RestClient restClient =
                RestClient.builder()
                        .baseUrl(mockWebServer.url("/").toString())
                        .build();

        restClientWrapper = new RestClientWrapper(restClient);
    }

    @AfterEach
    void tearDown() throws IOException {
        mockWebServer.shutdown();
    }

    @Test
    void shouldPerformGetRequest() {
        String expectedResponse = "{\"id\":1,\"name\":\"Test\"}";
        mockWebServer.enqueue(
                new MockResponse()
                        .setResponseCode(200)
                        .setBody(expectedResponse)
                        .addHeader("Content-Type", "application/json"));

        String response = restClientWrapper.get("/test", String.class);

        assertThat(response).isEqualTo(expectedResponse);
    }
}