package com.smartshop.repositories;

import com.smartshop.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,String> {
    // List<Product> findAllByCategory(String phone);

    @Query("Select category from Product group by category")
    List<String> getCategorys();
}
