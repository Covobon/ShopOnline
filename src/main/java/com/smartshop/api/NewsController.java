package com.smartshop.api;

import com.smartshop.model.News;
import com.smartshop.model.NewsImage;
import com.smartshop.repositories.NewsImageRepository;
import com.smartshop.repositories.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("/api/news")
public class NewsController {

    @Autowired
    private NewsRepository newsRepository;

    @Autowired
    private NewsImageRepository newsImageRepository;

    @GetMapping()
    public List<News> news(){
        return newsRepository.findAll();
    }

    @PostMapping()
    public void addNews(@RequestBody News news){
        newsRepository.save(news);
    }

    @PutMapping("/{theId}")
    public void updateNews(@RequestBody News news,@PathVariable("theId") int theId){
        List<NewsImage> newsImages = news.getNewsImages();
        newsImageRepository.deleteByNewId(theId);
        newsRepository.save(news);
        for (NewsImage newsImage : newsImages) {
            newsImageRepository.save(newsImage);
        }
    }
    @DeleteMapping("/{theId}")
    public void deleteNews(@PathVariable("theId") int theId){
        newsImageRepository.deleteByNewId(theId);
        newsRepository.deleteById(theId);
    }
}
