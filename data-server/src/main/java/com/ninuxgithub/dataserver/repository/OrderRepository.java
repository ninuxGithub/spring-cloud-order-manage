package com.ninuxgithub.dataserver.repository;

import com.ninuxgithub.dataserver.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, String> {

    @Query(value = "select o from Order o order by  o.createTime desc ")
    List<Order> findOrderListOrderByCreateTime();
}
