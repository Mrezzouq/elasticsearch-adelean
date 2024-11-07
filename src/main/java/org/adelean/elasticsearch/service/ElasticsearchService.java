package org.adelean.elasticsearch.service;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.adelean.elasticsearch.configuration.ElasticsearchClientProvider;
import org.elasticsearch.client.RestHighLevelClient;

public class ElasticsearchService implements ElasticsearchPort {
    private final RestHighLevelClient client;
    private final ObjectMapper objectMapper;

    public ElasticsearchService(ElasticsearchClientProvider elasticsearchClientProvider) {
        this.client = elasticsearchClientProvider.createClient();
        this.objectMapper = new ObjectMapper();
        this.objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    @Override
    public RestHighLevelClient getClient() {
        return client;
    }

    @Override
    public ObjectMapper getObjectMapper() {
        return objectMapper;
    }
}
