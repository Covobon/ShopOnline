package com.smartshop.dao.product;

import java.util.List;

public interface ProductImageDao {
    public void add(ProductImage productImage);
    public List<ProductImage> findAllByProduct(String productId);
    public void delete(ProductImage productImage);
    public void deleteByProduct(Product product);
}
