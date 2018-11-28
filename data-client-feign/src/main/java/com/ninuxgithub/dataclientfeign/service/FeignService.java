package com.ninuxgithub.dataclientfeign.service;


import com.ninuxgithub.dataclientfeign.model.Customer;
import com.ninuxgithub.dataclientfeign.model.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Component
@FeignClient(value = "data-server")
public interface FeignService {

    @RequestMapping(value = "/api/registry", method = RequestMethod.POST)
    public Map<String, Object> registry(@RequestBody Customer customer);


    @RequestMapping(value = "/api/login", method = RequestMethod.POST, consumes = "application/json")
    public Map<String, Object> login(@RequestBody Customer customer);

    @RequestMapping(value = "/api/productList", method = RequestMethod.GET)
    public List<Product> getProductList();

    @RequestMapping(value = "/api/findProductById", method = RequestMethod.GET, consumes = "application/json")
    public Product findProductById(@RequestParam(value = "id") String id);


}
