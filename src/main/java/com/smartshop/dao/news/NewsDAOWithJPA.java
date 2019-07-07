package com.smartshop.dao.news;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsDAOWithJPA extends JpaRepositoryImplementation<News, Integer> {
}
