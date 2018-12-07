package com.ninuxgithub.dataserver.service.impl;

import com.codingapi.tx.annotation.TxTransaction;
import com.ninuxgithub.dataserver.model.Customer;
import com.ninuxgithub.dataserver.model.Order;
import com.ninuxgithub.dataserver.model.Product;
import com.ninuxgithub.dataserver.repository.OrderRepository;
import com.ninuxgithub.dataserver.service.CustomerService;
import com.ninuxgithub.dataserver.service.OrderService;
import com.ninuxgithub.dataserver.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;


@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ProductService productService;

    @Autowired
    CustomerService customerService;

    @TxTransaction(isStart = true)
    @Transactional
    @Override
    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }

    /**
     * 开启事物， 如果订单保存失败
     * @param pid
     * @param uid
     * @return
     */
    @TxTransaction(isStart = true)
    @Transactional
    @Override
    public Order distributeSaveOrder(String pid, String uid) {
        Product product = productService.findProductById(pid);
        Customer customer = customerService.findCustomerById(uid);
        Order order = new Order();
        order.setCreateTime(new Date());
        order.setProduct(product);
        order.setCustomer(customer);
        Order reposOrder = orderRepository.save(order);
        if(reposOrder != null){
            int amount = product.getAmount();
            if(amount>0){
                product.setAmount(amount -1);
                productService.saveProduct(product);
            }else{
                throw new RuntimeException("商品的数量已经售罄!");
            }
        }
        return order;
    }

    @Override
    public List<Order> findOrderList() {
        return orderRepository.findOrderListOrderByCreateTime();
    }


}
