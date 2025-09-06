package com.christianjaena.spring_http_clients.core;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClient.RequestHeadersUriSpec;
import org.springframework.web.client.RestClient.ResponseSpec;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RestClientWrapperTest {

    @Mock
    private RestClient client;

    @InjectMocks
    private RestClientWrapper wrapper;

    @Test
    void shouldPerformGet() {
        RequestHeadersUriSpec requestSpec = mock(RequestHeadersUriSpec.class);
        RestClient.ResponseSpec responseSpec = mock(ResponseSpec.class);

        when(client.get()).thenReturn(requestSpec);
        when(requestSpec.uri(anyString())).thenReturn(requestSpec);
        when(requestSpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.body(String.class)).thenReturn("test");

        String response = wrapper.get("/test", String.class);

        assertEquals("test", response);

        verify(client).get();
        verify(requestSpec).uri("/test");
        verify(requestSpec).retrieve();
        verify(responseSpec).body(String.class);
    }
}