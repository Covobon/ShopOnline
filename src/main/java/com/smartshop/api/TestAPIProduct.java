package com.smartshop.api;

import com.smartshop.model.Product;
import com.smartshop.repositories.ProductDetailRepository;
import com.smartshop.repositories.ProductImageRepository;
import com.smartshop.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/product/test-crud")
public class TestAPIProduct {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductDetailRepository product_detail_repository;

    @Autowired
    private ProductImageRepository productImageRepository;

    @GetMapping()
    public List<Product> findAll(){
       return productRepository.findAll();
    }
    @GetMapping("/{theId}")
    public Product findById(@PathVariable("theId") String theId){
        Optional<Product> rs = productRepository.findById((theId));
        Product theProduct = null;
        if(rs.isPresent()){
            theProduct=rs.get();
        }
        return theProduct;
    }

    @PostMapping()
    public void addproduct(@RequestBody Product product){
        productRepository.save(product);
    }

    @PutMapping()
    public void updateProduct(@RequestBody Product product){
        String id = product.getProductId();
        productImageRepository.deleteByProductId(id);
        productRepository.save(product);
    }
    @DeleteMapping("/{theId}")
    public void deleteProduct(@PathVariable("theId") String theId){
        productImageRepository.deleteByProductId(theId);
        productRepository.deleteById(theId);
    }


}
