package com.smartshop.model;

import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="orders")
public class Orders {

    /*Fields*/
    @Id
    @Column(name="order_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderId;

    @Column(name="user_name")
    private String userName;

    @Column(name="status")
    private String status;

    @Column(name="address")
    private String address;

    @Column(name = "create_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;

    @OneToMany
    @JoinColumn(name = "order_id")
    private List<OrdersProduct> ordersProducts;

    @Column(name = "pay")
    @Nullable
    private int pay;

    /*Constructors*/
    public Orders() {
        Date date = new Date();
        this.createTime = date;
    }

    public Orders(String userName, String status, String address) {
        this.userName = userName;
        this.status = status;
        this.address = address;
        Date date = new Date();
        this.createTime = date;
    }

    /*Getters/Setters*/
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public java.util.Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(java.util.Date createTime) {
        this.createTime = createTime;
    }

    public List<OrdersProduct> getOrdersProducts() {
        return ordersProducts;
    }

    public void setOrdersProducts(List<OrdersProduct> ordersProducts) {
        this.ordersProducts = ordersProducts;
    }

    public int getPay() {
        return pay;
    }

    public void setPay(int pay) {
        this.pay = pay;
    }

    /*Methods*/
    @Override
    public String toString() {
        return "Orders{" +
                "orderId=" + orderId +
                ", userName='" + userName + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

}
