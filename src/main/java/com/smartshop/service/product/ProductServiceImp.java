package com.smartshop.service.product;

import com.smartshop.model.Product;
import com.smartshop.dao.product.ProductImageDao;
import com.smartshop.dao.product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ProductServiceImp implements ProductService{

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductImageDao productImageDao;

    @Override
    public List<Product> findAll() {
        return null;
    }

    @Override
    public Product getById(String productId) {
        return null;
    }

    @Override
    public void create() {

    }

    @Override
    public void deleteById() {

    }
}
