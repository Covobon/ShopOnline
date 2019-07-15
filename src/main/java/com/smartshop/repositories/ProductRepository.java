package com.smartshop.repositories;

import com.smartshop.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,String> {
    // List<Product> findAllByCategory(String phone);
}
