package com.ninuxgithub.dataserver.service.impl;

import com.ninuxgithub.dataserver.model.Order;
import com.ninuxgithub.dataserver.repository.OrderRepository;
import com.ninuxgithub.dataserver.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Override
    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public List<Order> findOrderList() {
        return orderRepository.findOrderListOrderByCreateTime();
    }


}
