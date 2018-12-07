package com.ninuxgithub.dataserver.api;


import com.ninuxgithub.dataserver.model.Order;
import com.ninuxgithub.dataserver.service.CustomerService;
import com.ninuxgithub.dataserver.service.OrderService;
import com.ninuxgithub.dataserver.service.ProductService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrderController {

    @Autowired
    OrderService orderService;

    @Autowired
    ProductService productService;

    @Autowired
    CustomerService customerService;

    @RequestMapping("/api/saveOrder")
    public Order saveOrder(@RequestParam("pid") String pid, @RequestParam("uid") String uid) {
        if (StringUtils.isNotBlank(pid) && StringUtils.isNotBlank(uid)) {
            Order reposOrder=null;
            try {
                //通过orderService 将订单保存
                reposOrder =orderService.distributeSaveOrder(pid,uid);
            }catch (Exception e){
                e.printStackTrace();
            }
            return reposOrder;
        }
        return null;
    }

    @RequestMapping(value ="/api/findOrderList",method =  {RequestMethod.GET, RequestMethod.POST})
    public List<Order> findOrderList(){
        return orderService.findOrderList();
    }
}
