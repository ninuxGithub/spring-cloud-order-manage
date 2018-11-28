package com.ninuxgithub.dataclientfeign.model;

import javax.validation.constraints.NotNull;
import java.io.Serializable;


public class Product implements Serializable {

    private static final long serialVersionUID = 2709915548428796328L;

    private String id;

    private String productName;

    private Double price;

    private String description;

    private String imgUrl;

    private Type type;

    private int amount;

    public Product() {
    }

    public Product(String productName, Double price, String description, String imgUrl, @NotNull Type type, int amount) {
        this.productName = productName;
        this.price = price;
        this.description = description;
        this.imgUrl = imgUrl;
        this.type = type;
        this.amount = amount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
