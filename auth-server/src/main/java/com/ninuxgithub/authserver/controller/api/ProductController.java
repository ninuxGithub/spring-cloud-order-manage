package com.ninuxgithub.authserver.controller.api;


import com.ninuxgithub.authserver.model.Product;
import com.ninuxgithub.authserver.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    ProductRepository productRepository;


    @RequestMapping(value = "/api/productList", method = RequestMethod.GET)
    public List<Product> getProductList() {
        return productRepository.findAll();
    }


}
