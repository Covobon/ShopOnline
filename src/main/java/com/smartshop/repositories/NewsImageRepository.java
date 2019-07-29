package com.smartshop.repositories;

import com.smartshop.model.News;
import com.smartshop.model.NewsImage;
import com.smartshop.model.NewsImageid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface NewsImageRepository extends JpaRepository<NewsImage, NewsImageid> {
    @Transactional
    public  void deleteByNewId(int newId);
}
