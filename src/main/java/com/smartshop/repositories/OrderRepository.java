package com.smartshop.repositories;

import com.smartshop.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Date;

@Repository
@Transactional
public interface OrderRepository extends JpaRepository<Order, Integer> {

    @Modifying
    @Query(value = "insert into `order`(`address`, `create_time`, `status`, `user_name`) value (?, ?, ?, ?)", nativeQuery = true)
    void customSave(String address, Date date, String status, String username);

}
