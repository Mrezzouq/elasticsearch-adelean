package org.adelean.elasticsearch.service;

import org.adelean.elasticsearch.model.Product;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.common.xcontent.XContentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

public class IndexationService implements Indexation {
    private static final Logger logger = LoggerFactory.getLogger(IndexationService.class);
    private final ElasticsearchPort elasticsearchPort;
    private final String indexName;

    public IndexationService(ElasticsearchPort elasticsearchPort, String indexName) {
        this.elasticsearchPort = elasticsearchPort;
        this.indexName = indexName;
    }

    @Override
    public void indexProducts(List<Product> products) {
        var bulkRequest = createBulkRequest(products);
        executeBulkRequest(bulkRequest);
    }

    private BulkRequest createBulkRequest(List<Product> products) {
        var bulkRequest = new BulkRequest();
        for (var product : products) {
            try {
                var indexRequest = createIndexRequest(product);
                bulkRequest.add(indexRequest);
            } catch (IOException e) {
                logger.error("Failed to serialize product: {}", product, e);
            }
        }
        return bulkRequest;
    }

    private IndexRequest createIndexRequest(Product product) throws IOException {
        return new IndexRequest(indexName)
                .source(elasticsearchPort.getObjectMapper().writeValueAsString(product), XContentType.JSON);
    }

    private void executeBulkRequest(BulkRequest bulkRequest) {
        try {
            var bulkResponse = elasticsearchPort.getClient().bulk(bulkRequest, RequestOptions.DEFAULT);
            if (bulkResponse.hasFailures()) {
                logger.error("Failed to index some documents: {}", bulkResponse.buildFailureMessage());
            } else {
                logger.info("Successfully indexed all documents.");
            }
        } catch (IOException e) {
            logger.error("Failed to execute bulk request", e);
        }
    }

    @Override
    public void close() {
        try {
            elasticsearchPort.getClient().close();
        } catch (IOException e) {
            logger.error("Failed to close Elasticsearch client", e);
        }
    }
}
