package com.smartshop.model;


public class Cart {

    /*Fields*/
    private int cartId;
    private String userName;
    private String address;

    /*Constructors*/
    public Cart() {
    }

    public Cart(int cartId, String userName, String address) {
        this.cartId = cartId;
        this.userName = userName;
        this.address = address;
    }

    /*Getters/Setters*/
    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    /*Methods*/
    @Override
    public String toString() {
        return "Cart{" +
                "cartId=" + cartId +
                ", userName='" + userName + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
