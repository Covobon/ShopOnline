/*
package com.smartshop.api;

import com.smartshop.model.Product;
import com.smartshop.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping
    public List<Product> getAll(){
        return productRepository.findAll();
    }

    @GetMapping("/phone")
    public List<Product> getAllPhone() {
        return productRepository.findAllByCategory("Phone");
    }
}
*/
