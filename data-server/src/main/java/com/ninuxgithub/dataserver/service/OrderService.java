package com.ninuxgithub.dataserver.service;

import com.ninuxgithub.dataserver.model.Order;

import java.util.List;

public interface OrderService {

    Order saveOrder(Order order);

    List<Order> findOrderList();
}
