package com.smartshop.api;

import com.smartshop.repositories.ProductRepository;
import com.smartshop.service.ProductService;
import com.smartshop.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/api/info")
public class InfoController {

    @Autowired
    private StorageService storageService;

    @Autowired
    private ProductService productService;

    @GetMapping("/category")
    public List<String> getCategorys() {
        return productService.getCategorys();
    }
}
