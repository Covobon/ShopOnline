package com.smartshop.repositories;

import com.smartshop.model.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,String> {

    @Query("Select category from Product group by category")
    List<String> getCategorys();

    /*@Query(value = "select * from product where product_id like ? and name like ? and category like ? " +
            "and price between ? and ? limit ?, ?",
            nativeQuery = true)
    List<Product> find(String productId, String name, String category, int min, int max, int start, int number);
    */

    List<Product> findAllByProductIdLikeAndNameLikeAndCategoryLikeAndPriceBetween
            (String productId, String name, String category, int priceStart, int priceEnd, Pageable page);
}
