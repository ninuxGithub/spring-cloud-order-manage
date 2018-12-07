package com.ninuxgithub.dataserver.service.impl;

import com.codingapi.tx.annotation.TxTransaction;
import com.ninuxgithub.dataserver.model.Product;
import com.ninuxgithub.dataserver.repository.ProductRepository;
import com.ninuxgithub.dataserver.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;
    @Override
    public List<Product> findProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product findProductById(String id) {
        return productRepository.findById(id).orElse(null);
        //return productRepository.findOne(id);
    }

    @TxTransaction(isStart = true)
    @Transactional
    @Override
    public void saveProduct(Product product) {
        productRepository.save(product);
    }
}
