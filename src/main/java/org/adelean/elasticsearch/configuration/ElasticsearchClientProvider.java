package org.adelean.elasticsearch.configuration;

import org.elasticsearch.client.RestHighLevelClient;

public interface ElasticsearchClientProvider {
    RestHighLevelClient createClient();
    void closeClient();
}
