package org.adelean.elasticsearch.service;

import org.adelean.elasticsearch.model.Product;

import java.util.List;

public interface Indexation {
    void indexProducts(List<Product> products);
    void close();
}
