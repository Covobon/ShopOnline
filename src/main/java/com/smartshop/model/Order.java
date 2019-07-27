package com.smartshop.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="order")
public class Order {

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
    private java.util.Date createTime;

    /*Constructors*/
    public Order() {
    }

    public Order(String userName, String status, String address, Date createTime) {
        this.userName = userName;
        this.status = status;
        this.address = address;
        this.createTime = createTime;
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

    /*Methods*/
    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", userName='" + userName + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

}
