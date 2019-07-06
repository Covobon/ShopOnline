package com.smartshop.api;

import com.smartshop.dao.product.Product;
import com.smartshop.dao.product.ProductImage;
import com.smartshop.dao.product.ProductImageDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productimage")
public class ProductImageController {

    @Autowired
    private ProductImageDao productImageDao;
    //TODO----------
    @GetMapping("/{productId}")
    public List<ProductImage> findAll(@PathVariable("productId") String productId) {
        return productImageDao.findAllByProduct(productId);
    }
    //TODO------

    @DeleteMapping
    public void deleteByProduct(@RequestBody Product product){
        productImageDao.deleteByProduct(product);
    }
}

