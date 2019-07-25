package com.smartshop.service;

import com.smartshop.model.Product;
import com.smartshop.repositories.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.*;

@Service
public class ProductService {

    Logger log = LoggerFactory.getLogger(this.getClass().getName());


    @Autowired
    private ProductRepository productRepository;
    public List<Product> find(Map<String, String> allParams){
        String productId, name, category;
        int min = Integer.MIN_VALUE;
        int max = Integer.MAX_VALUE;
        int start = 0;
        int pageSize = Integer.MAX_VALUE;
        if (allParams.get("productId") != null) {
             productId = allParams.get("productId");
        } else {
            productId = "%";
        }

       if (allParams.get("name") != null) {
             name = generateName(allParams.get("name"));
        } else {
           name = "%";
       }

        if (allParams.get("category") != null) {
            category = allParams.get("category");
        } else {
            category = "%";
        }

        if (allParams.get("price") != null) {
            String first = allParams.get("price").substring(0, allParams.get("price").indexOf("and"));
            min = Integer.parseInt(first);
            String second = allParams.get("price").substring(allParams.get("price").indexOf("and") + 3);
            max = Integer.parseInt(second);
        }

        List<Sort.Order> orders = new ArrayList<>();
        if (allParams.get("orderby") != null) {
            String s = allParams.get("orderby");
            do {
                log.info(allParams.get("orderby"));
                String filed = s.substring(0, s.indexOf("-"));
                String cache = s.substring(s.indexOf("-") + 1);
                String way;
                if (cache.indexOf(",") == -1){
                    way = cache;
                    s = "";
                } else {
                    way = cache.substring(0, cache.indexOf(","));
                    s = s.substring(s.indexOf(",") + 1);
                }
                 log.info("Filed: " + filed + "/nWay: " + way);

                if (way.equals("asc")) {
                    orders.add(new Sort.Order(Sort.Direction.ASC, filed));
                } orders.add(new Sort.Order(Sort.Direction.DESC, filed));
            }while (!s.equals(""));
        }

        Sort sort = Sort.by(orders);

        Pageable panigation = PageRequest.of(0, Integer.MAX_VALUE, sort);
        if (allParams.get("pageSize") != null) {
            pageSize = Integer.parseInt(allParams.get("pageSize"));
            if (allParams.get("page") != null) {
                int page = Integer.parseInt(allParams.get("page"));
                panigation = PageRequest.of(page - 1, pageSize);
            }
        }

        return productRepository.findAllByProductIdLikeAndNameLikeAndCategoryLikeAndPriceBetween
                (productId, name,  category, min,  max, panigation);
    }


    public List<Product> findAll() {
        return productRepository.findAll();
    }

/*    public List<Product> getByPage(int page, int pageSize, String[] args){
        String orderBy = "";
        for (String arg of args)

        }
    }*/

    private String generateName(String name) {
        Queue<Character> queue = new LinkedList<>();
        queue.add('%');
        for (int i = 0; i < name.length(); i ++) {
            queue.add(name.charAt(i));
            queue.add('%');
        }
        String result = "";
        while (!queue.isEmpty()) {
            result = result + queue.remove();
        }
        return  result;
    }

    private String generatePrice(String price) {
        int indexOfAnd = price.indexOf("and");
        String min  = price.substring(0, indexOfAnd);
        String max = price.substring(indexOfAnd + 3);
        return min + " and " + max;
    }





    public List<String> getCategorys() {
        return productRepository.getCategorys();
    }


}
