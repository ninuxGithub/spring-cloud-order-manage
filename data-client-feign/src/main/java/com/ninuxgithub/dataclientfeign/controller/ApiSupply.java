package com.ninuxgithub.dataclientfeign.controller;


import com.ninuxgithub.dataclientfeign.model.Customer;
import com.ninuxgithub.dataclientfeign.model.Order;
import com.ninuxgithub.dataclientfeign.model.Product;
import com.ninuxgithub.dataclientfeign.service.FeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class ApiSupply {

    @Autowired
    FeignService feignService;


//    @RequestMapping(value = "/api/registry", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
//    public Map<String, Object> registry(Map<String,Object> params) {
//        System.err.println(params);
//        return null;
//    }
//    @RequestMapping(value = "/api/registry", method = RequestMethod.POST, consumes = "application/x-www-form-urlencoded;charset=UTF-8", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
//    public Map<String, Object> registry(Customer customer) {
//        return feignService.registry(customer);
//    }


    @RequestMapping(value = "/api/registry", method = RequestMethod.POST, consumes = "application/json")
    public Map<String, Object> registry(@RequestBody Customer customer) {
        return feignService.registry(customer);

    }

    @RequestMapping(value = "/api/login", method = RequestMethod.POST, consumes = "application/json")
    public Map<String, Object> login(@RequestBody Customer customer) {
        System.out.println(customer);
        return feignService.login(customer);
    }

    @RequestMapping(value = "/api/productList", method = RequestMethod.GET)
    public List<Product> getProductList() {
        return feignService.getProductList();
    }

    @RequestMapping(value = "/api/findProductById", method = RequestMethod.GET)
    public Product findProductById(@RequestParam(value = "id") String id) {
        return feignService.findProductById(id);
    }

    @RequestMapping(value = "/api/saveOrder", method = RequestMethod.GET)
    public Order saveOrder(@RequestParam("pid") String pid, @RequestParam("uid") String uid) {
        return feignService.saveOrder(pid, uid);
    }

    @RequestMapping(value = "/api/findOrderList",method =  {RequestMethod.GET, RequestMethod.POST})
    public List<Order> findOrderList() {
        return feignService.findOrderList();
    }


}
