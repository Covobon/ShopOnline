package com.smartshop.model;

import java.sql.Date;

public class Order {

    /*Fields*/
    private int orderId;
    private String userName;
    private String status;
    private Date date;

    /*Constructors*/
    public Order() {
    }

    public Order(int orderId, String userName, String status, Date date) {
        this.orderId = orderId;
        this.userName = userName;
        this.status = status;
        this.date = date;
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

    public Date getDate() {
        return date;
    }

    /*Methods*/
    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", userName='" + userName + '\'' +
                ", status='" + status + '\'' +
                ", date=" + date +
                '}';
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
