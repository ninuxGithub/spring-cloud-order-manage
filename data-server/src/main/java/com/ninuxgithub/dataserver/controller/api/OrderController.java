package com.ninuxgithub.dataserver.controller.api;


import com.ninuxgithub.dataserver.model.Order;
import com.ninuxgithub.dataserver.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    @Autowired
    OrderService orderService;

    @RequestMapping("/api/saveOrder")
    public Order saveOrder(@RequestBody Order order) {
        return orderService.saveOrder(order);
    }
}
