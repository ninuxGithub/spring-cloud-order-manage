package com.ninuxgithub.dataclientfeign.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 坑：  表的名称不能为关键字： order
 */
public class Order implements Serializable {

    private static final long serialVersionUID = -4298152122761680527L;

    private String id;


    private Product product;

    private Customer customer;

    private Date createTime;

    public Order() {
    }

    public Order(Product product, Customer customer, Date createTime) {
        this.product = product;
        this.customer = customer;
        this.createTime = createTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", product=" + product +
                ", customer=" + customer +
                ", createTime=" + createTime +
                '}';
    }
}
