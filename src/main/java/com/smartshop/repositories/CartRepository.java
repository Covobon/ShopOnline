package com.smartshop.repositories;

import com.smartshop.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

@Transactional
public interface CartRepository extends JpaRepository<Cart, Integer> {

    @Modifying
    @Query(value="delete from cart_product where cart_id = ?", nativeQuery = true)
    void cleanCart(int cartId);

    @Modifying
    @Query(value="insert into cart_product(`cart_id`, `product_id`, `amount`) value (?, ?, ?)", nativeQuery = true)
    void addCart(int cartId, String productId, int amount);

    @Modifying
    @Query(value="update cart_product set amount = ? where cart_id = ? and product_id = ? ", nativeQuery = true)
    void modifyAmount(int amount, int cartId, String productId);

    @Modifying
    @Query(value = "delete from cart_product where cart_id = ? and product_id = ?", nativeQuery = true)
    void removeFormCar(int cartId, String productId);
}
