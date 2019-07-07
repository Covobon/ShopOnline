package com.smartshop.service.NewService;

import com.smartshop.dao.news.NewsImage;

public interface NewsImageServices {

    public void addNewsImage(NewsImage theNewsImage);

    public  void update(NewsImage newsImage);
    public void delete(int theId);
}
