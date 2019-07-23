package com.smartshop.service;

import com.smartshop.model.Product;
import com.smartshop.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> findAll() {
        return productRepository.findAll();
    }

/*    public List<Product> getByPage(int page, int pageSize, String[] args){
        String orderBy = "";
        for (String arg of args)

        }
    }*/

    public List<String> getCategorys() {
        return productRepository.getCategorys();
    }


}
