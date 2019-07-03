package com.smartshop.dao.cart;

public class CartProduct {

    /*Fields*/
    private String userName;
    private String productId;
    private int amount;

    /*Constructors*/
    public CartProduct() {
    }

    public CartProduct(String userName, String productId, int amount) {
        this.userName = userName;
        this.productId = productId;
        this.amount = amount;
    }

    /*Getters/Setters*/
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }


    /*Methods*/
    @Override
    public String toString() {
        return "CartProduct{" +
                "userName='" + userName + '\'' +
                ", productId='" + productId + '\'' +
                ", amount=" + amount +
                '}';
    }
}
