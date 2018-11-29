package com.ninuxgithub.dataclientfeign.service;


import com.ninuxgithub.dataclientfeign.model.Customer;
import com.ninuxgithub.dataclientfeign.model.Order;
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

    /**
     * 前台用户注册
     *
     * @param customer
     * @return
     */
    @RequestMapping(value = "/api/registry", method = RequestMethod.POST, consumes = "application/json")
    public Map<String, Object> registry(@RequestBody Customer customer);


    /**
     * 前台用户登录
     *
     * @param customer
     * @return
     */
    @RequestMapping(value = "/api/login", method = RequestMethod.POST, consumes = "application/json")
    public Map<String, Object> login(@RequestBody Customer customer);


    /**
     * 前台获取产品列表
     *
     * @return
     */
    @RequestMapping(value = "/api/productList", method = RequestMethod.GET)
    public List<Product> getProductList();


    /**
     * 根据产品ID查询产品对象
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/api/findProductById", method = RequestMethod.GET, consumes = "application/json")
    public Product findProductById(@RequestParam(value = "id") String id);

    /**
     * 保存订单
     *
     * @param pid
     * @param uid
     * @return
     */
    @RequestMapping(value = "/api/saveOrder", method = RequestMethod.GET, consumes = "application/json")
    public Order saveOrder(@RequestParam("pid") String pid, @RequestParam("uid") String uid);


    /**
     * 获取订单列表
     * @return
     */
    @RequestMapping(value ="/api/findOrderList",method =  {RequestMethod.POST})
    public List<Order> findOrderList();


}
