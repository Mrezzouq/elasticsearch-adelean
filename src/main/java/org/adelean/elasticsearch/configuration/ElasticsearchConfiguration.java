package org.adelean.elasticsearch.configuration;

import org.adelean.elasticsearch.utils.ConfigurationProvider;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ElasticsearchConfiguration implements ElasticsearchClientProvider {
    private static final Logger logger = LoggerFactory.getLogger(ElasticsearchConfiguration.class);
    private final ConfigurationProvider configurationProvider;
    private RestHighLevelClient client;

    public ElasticsearchConfiguration(ConfigurationProvider configurationProvider) {this.configurationProvider = configurationProvider;}

    private static RestClientBuilder buildRestClient(String host, int port, String scheme) {
        return RestClient.builder(new HttpHost(host, port, scheme))
                .setRequestConfigCallback(requestConfigBuilder -> requestConfigBuilder
                        .setConnectTimeout(5000)
                        .setSocketTimeout(60000));
    }

    @Override
    public RestHighLevelClient createClient() {
        if (client == null) {
            try {
                var host = configurationProvider.get("elasticsearch.host");
                var port = Integer.parseInt(configurationProvider.get("elasticsearch.port"));
                var scheme = configurationProvider.get("elasticsearch.scheme");

                client = new RestHighLevelClient(
                        buildRestClient(host, port, scheme)
                );
            } catch (Exception e) {
                logger.error("Failed to create Elasticsearch client", e);
            }
        }
        return client;
    }

    @Override
    public void closeClient() {
        if (client != null) {
            try {
                client.close();
            } catch (Exception e) {
                logger.error("Failed to close Elasticsearch client", e);
            }
        }

    }
}
