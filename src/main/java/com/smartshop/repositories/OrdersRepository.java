package com.smartshop.repositories;

import com.smartshop.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Date;

@Repository
@Transactional
public interface OrdersRepository extends JpaRepository<Orders, Integer> {


    @Modifying
    @Query(value = "insert into `order_product`(order_id, product_id, amount) value (?, ?, ?)", nativeQuery = true)
    void addProduct(int orderId, String productId, int amount);


    Orders findByUserNameAndCreateTime(String userName, Date createTime);
}
