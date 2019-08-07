package com.smartshop.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "order_product")
public class OrdersProduct implements Serializable {

    @Id
    @Column(name = "order_id")
    private String ordersId;

    @Id
    @Column(name = "product_id")
    private String product;

    @Column(name = "amount")
    private int amount;

    public String getOrdersId() {
        return ordersId;
    }

    public void setOrdersId(String ordersId) {
        this.ordersId = ordersId;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
