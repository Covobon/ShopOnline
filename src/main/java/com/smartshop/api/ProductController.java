package com.smartshop.api;

import com.smartshop.model.Product;
import com.smartshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/product")
@CrossOrigin(origins = "http://localhost:4200")

public class ProductController{

    @Autowired
    private ProductService productService;

    @GetMapping
    public List<Product> find(@RequestParam Map<String, String> allParams) {
        return productService.find(allParams);
    }

}
