package com.smartshop.dao.news;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsDAOWithJPA extends JpaRepository<News, Integer> {
}
