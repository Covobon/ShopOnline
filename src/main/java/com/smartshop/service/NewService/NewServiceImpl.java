package com.smartshop.service.NewService;

import com.smartshop.dao.news.News;
import com.smartshop.dao.news.NewsDAOWithJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NewServiceImpl implements NewServices {

    @Autowired
    private NewsDAOWithJPA newsDAOWithJPA;

    @Override
    public List<News> getAllNews() {
        return newsDAOWithJPA.findAll();
    }
    @Override
    public Optional<News> findNewsById(int theId) {
        return newsDAOWithJPA.findById(theId);
    }

    @Override
    public News addNews(News theNews) {
        newsDAOWithJPA.save(theNews);
        return theNews;
    }

    @Override
    public void deleteNew(int theId) {
        newsDAOWithJPA.deleteById(theId);
    }
}
