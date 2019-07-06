package com.smartshop.api;

import com.smartshop.dao.news.News;
import com.smartshop.dao.news.NewsImage;
import com.smartshop.service.NewService.NewServices;
import com.smartshop.service.NewService.NewsImageServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/news")
public class TestApiNews {

    @Autowired
    private NewServices newServices;

    @Autowired
    private NewsImageServices newsImageServices;
    @GetMapping()
    public List<News> getAll(){
        return newServices.getAllNews();
    }
    @GetMapping("/list1")
    public List<News> getlist(){
        return newServices.getAll();
    }
    //TODO---
    @PutMapping()
    public News editNews( @RequestBody News news){
        return newServices.addNews(news);
    }
    @PostMapping()
    public void addNews(@RequestBody News news){
        newServices.addNews(news);
    }
    @DeleteMapping("/{theId}")
    public void delete(@PathVariable("theId") int theId) {
        newServices.deleteNew(theId);
    }
    //TODO---
}
