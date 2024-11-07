package org.adelean.elasticsearch.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.elasticsearch.client.RestHighLevelClient;

public interface ElasticsearchPort {
    RestHighLevelClient getClient();

    ObjectMapper getObjectMapper();
}
