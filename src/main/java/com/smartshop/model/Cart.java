package com.smartshop.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="cart")
public class Cart {

    /*Fields*/
    @Id
    @Column(name="cart_id")
    private int cartId;

    @Column(name="address")
    private String address;

    /*Constructors*/
    public Cart() {
    }

    public Cart(int cartId, String userName, String address) {
        this.cartId = cartId;
        this.address = address;
    }

    /*Getters/Setters*/
    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
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
                ", address='" + address + '\'' +
                '}';
    }
}
