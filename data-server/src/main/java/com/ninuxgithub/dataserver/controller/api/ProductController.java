package com.ninuxgithub.dataserver.controller.api;


import com.ninuxgithub.dataserver.model.Product;
import com.ninuxgithub.dataserver.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    ProductService productService;


    @RequestMapping(value = "/api/productList", method = RequestMethod.GET)
    public List<Product> getProductList() {
        return productService.findProducts();
    }

    @RequestMapping(value = "/api/findProductById", method = RequestMethod.GET)
    public Product findProductById(@RequestParam(value = "id") String id) {
        return productService.findProductById(id);
    }


}
