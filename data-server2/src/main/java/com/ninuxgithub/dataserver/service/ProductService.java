package com.ninuxgithub.dataserver.service;

import com.ninuxgithub.dataserver.model.Product;

import java.util.List;

public interface ProductService {

    List<Product> findProducts();

    Product findProductById(String id);

    void saveProduct(Product product);
}
