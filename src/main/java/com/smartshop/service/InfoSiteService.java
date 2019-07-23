package com.smartshop.service;

import com.smartshop.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InfoSiteService {

    @Autowired
    private ProductRepository productRepository;

    public List<String> getCategory(){
        return productRepository.getCategory();
    }
}
