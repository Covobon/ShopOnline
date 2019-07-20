package com.smartshop.model;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="cart")
public class Cart {

    /*Fields*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="cart_id")
    private int cartId;

    @Column(name="address")
    private String address;


    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL, mappedBy = "cart")
    private List<CartProduct> cartProduct;
    /*Constructors*/
    public Cart() {
    }

    public Cart(String address) {
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

    public List<CartProduct> getCartProduct() {
        return cartProduct;
    }

    public void setCartProduct(List<CartProduct> cartProduct) {
        this.cartProduct = cartProduct;
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
