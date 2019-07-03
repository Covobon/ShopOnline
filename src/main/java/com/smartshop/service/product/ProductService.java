package com.smartshop.service.product;

import com.smartshop.dao.product.Product;

import java.util.List;

public interface ProductService {
    public List<Product> findAll();
    public Product getById(String productId);
    public void create();
    public void deleteById();
}
