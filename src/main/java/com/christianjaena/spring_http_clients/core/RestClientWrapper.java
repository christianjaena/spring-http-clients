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

    @Override
    public <T> T post(String url, Object body, Class<T> responseType) {
        return null;
    }

    @Override
    public <T> T put(String url, Object body, Class<T> responseType) {
        return null;
    }

    @Override
    public <T> T patch(String url, Object body, Class<T> responseType) {
        return null;
    }

    @Override
    public void delete(String url) {

    }
}
