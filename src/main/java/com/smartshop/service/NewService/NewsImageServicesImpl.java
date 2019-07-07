package com.smartshop.service.NewService;

import com.smartshop.dao.news.NewsImage;
import com.smartshop.dao.news.NewsImageRepository;
import org.springframework.stereotype.Service;

@Service
public class NewsImageServicesImpl implements NewsImageServices {

    private NewsImageRepository newsImageRepository;
    @Override
    public void addNewsImage(NewsImage theNewsImage) {
        newsImageRepository.save(theNewsImage);
    }

    @Override
    public void update(NewsImage newsImage) {
        newsImageRepository.delete(newsImage);
    }

    @Override
    public void delete(int theId) {
        newsImageRepository.deleteById(theId);

    }
}
