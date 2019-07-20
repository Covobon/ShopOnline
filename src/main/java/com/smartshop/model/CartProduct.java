package com.smartshop.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "cart_product")
public class CartProduct implements Serializable {

    @Id
    @Column(name = "cart_id")
    private int cartId;

    @Id
    @Column(name = "product_id")
    private String productId;

    @Column(name = "amount")
    private int amount;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    /*Constructors*/
    public CartProduct() {
    }

    public CartProduct(int cartId, String productId, int amount) {
        this.cartId = cartId;
        this.productId = productId;
        this.amount = amount;
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
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
}
