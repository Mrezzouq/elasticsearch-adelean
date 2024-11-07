package org.adelean.elasticsearch;

import org.adelean.elasticsearch.configuration.ElasticsearchClientProvider;
import org.adelean.elasticsearch.configuration.ElasticsearchConfiguration;
import org.adelean.elasticsearch.parser.XmlParser;
import org.adelean.elasticsearch.service.ElasticsearchService;
import org.adelean.elasticsearch.service.Indexation;
import org.adelean.elasticsearch.service.IndexationService;
import org.adelean.elasticsearch.utils.ConfigurationUtils;
import org.adelean.elasticsearch.utils.UnzipUtils;

import javax.xml.stream.XMLInputFactory;
import java.io.FileInputStream;
import java.io.IOException;

public class Main {
    private final UnzipUtils unzipUtils;
    private final XmlParser xmlParser;
    private final Indexation indexation;
    private final ElasticsearchClientProvider elasticsearchClientProvider;

    public Main(UnzipUtils unzipUtils, XmlParser xmlParser, Indexation indexation, ElasticsearchClientProvider elasticsearchClientProvider) {
        this.unzipUtils = unzipUtils;
        this.xmlParser = xmlParser;
        this.indexation = indexation;
        this.elasticsearchClientProvider = elasticsearchClientProvider;
    }


    public static void main(String[] args) {
        var zipFilePath = "src/main/resources/fixtures/xml.zip";
        var outputDirectory = "resources/fixtures";
        var indexName = "products";

        var xmlInputFactory = XMLInputFactory.newInstance();
        var xmlParser = new XmlParser(xmlInputFactory);
        var elasticsearchClientProvider = new ElasticsearchConfiguration(new ConfigurationUtils());
        var elasticsearchService = new ElasticsearchService(elasticsearchClientProvider);
        var indexationService = new IndexationService(elasticsearchService, indexName);
        var unzipUtils = new UnzipUtils();

        var pipeline = new Main(unzipUtils, xmlParser, indexationService, elasticsearchClientProvider);
        pipeline.run(zipFilePath, outputDirectory);
    }

    public void run(String zipFilePath, String outputDirectory) {
        String xmlFilePath;

        try {
            xmlFilePath = unzipUtils.unzip(zipFilePath, outputDirectory);

            try (var inputStream = new FileInputStream(xmlFilePath)) {
                var products = xmlParser.parseXmlFile(inputStream);

                indexation.indexProducts(products);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            elasticsearchClientProvider.closeClient();
        }
    }
}