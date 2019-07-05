package com.smartshop.service.NewService;

import com.smartshop.dao.news.*;

import java.util.List;
import java.util.Optional;

public interface NewServices {

    public List<News> getAllNews();
    public Optional<News> findNewsById(int theId );
    public News addNews(News theNews);
    public void deleteNew(int theId);
}
