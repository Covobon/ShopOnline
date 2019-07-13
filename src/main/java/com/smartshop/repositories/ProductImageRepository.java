package com.smartshop.repositories;

import com.smartshop.model.ProductImage;
import com.smartshop.model.ProductImageID;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

public interface ProductImageRepository extends JpaRepository<ProductImage, ProductImageID> {
    @Transactional
    void deleteByProductId(String theId);
}
