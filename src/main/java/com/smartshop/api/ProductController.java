package com.smartshop.api;

import com.smartshop.model.Product;
import com.smartshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/product")
public class ProductController{

    @Autowired
    private ProductService productService;

    @GetMapping
    public List<Product> find(@RequestParam Map<String, String> allParams) {
        return productService.find(allParams);
    }

}
