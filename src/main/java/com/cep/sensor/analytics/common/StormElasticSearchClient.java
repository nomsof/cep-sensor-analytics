package com.cep.sensor.analytics.common;

import java.io.Serializable;

import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;

public final class StormElasticSearchClient implements Serializable {

    private final EsConfig esConfig;

    public StormElasticSearchClient(EsConfig esConfig) {
        this.esConfig = esConfig;
    }

    public RestClient construct() {
        RestClientBuilder builder = RestClient.builder(esConfig.getHttpHosts());
        if (esConfig.getMaxRetryTimeoutMillis() != null) {
            builder.setMaxRetryTimeoutMillis(esConfig.getMaxRetryTimeoutMillis());
        }
        if (esConfig.getDefaultHeaders() != null) {
            builder.setDefaultHeaders(esConfig.getDefaultHeaders());
        }
        if (esConfig.getFailureListener() != null) {
            builder.setFailureListener(esConfig.getFailureListener());
        }
        if (esConfig.getHttpClientConfigCallback() != null) {
            builder.setHttpClientConfigCallback(esConfig.getHttpClientConfigCallback());
        }
        if (esConfig.getRequestConfigCallback() != null) {
            builder.setRequestConfigCallback(esConfig.getRequestConfigCallback());
        }
        if (esConfig.getPathPrefix() != null) {
            builder.setPathPrefix(esConfig.getPathPrefix());
        }
        return builder.build();
    }
}