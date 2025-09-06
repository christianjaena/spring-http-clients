package com.christianjaena.spring_http_clients.core;

import org.springframework.web.client.RestClient;

public class RestClientWrapper implements HttpClientWrapper {

    private final RestClient restClient;

    public RestClientWrapper(RestClient restClient) {
        this.restClient = restClient;
    }

    @Override
    public <T> T get(String url, Class<T> responseType) {
        return this.restClient
                .get()
                .uri(url)
                .retrieve()
                .body(responseType);
    }

}
