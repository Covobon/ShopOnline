package com.smartshop.service;

import com.smartshop.model.Cart;
import com.smartshop.model.CartProduct;
import com.smartshop.model.Product;
import com.smartshop.repositories.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    public Cart findById(int cartId) {
        return cartRepository.findById(cartId).orElse(null);
    }

    public void update(List<Product> products, int cartId){
        cartRepository.cleanCart(cartId);
        for (Product product : products) {
            cartRepository.addCart(cartId, product.getProductId(), product.getAmount());
        }
    }

    public void addToCart(int cartId, String productId, int amount){
        cartRepository.addCart(cartId, productId, amount);
    }

    public void modifyAmount(int cartId, String productId, int amount){
        cartRepository.modifyAmount(amount, cartId, productId);
    }

    public void removeFormCart(int cartId, String productId) {
        cartRepository.removeFormCar(cartId, productId);
    }

    public void clean(int cartId){
        cartRepository.cleanCart(cartId);
    }

    public void save(Cart cart) {
        cartRepository.save(cart);
    }
}
