package com.smartshop.repositories;

import com.smartshop.model.ProductDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface ProductDetailRepository extends JpaRepository<ProductDetail,String> {
   @Transactional
     void deleteById(String theId);
}
