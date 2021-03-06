package com.smartshop.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="news")
public class News {
    /*Fields*/
    @Id
    @Column(name="new_id")
    private int newId;

    @Column(name="title")
    private String title;

    @Column(name="content")
    private String content;

    @OneToMany(fetch= FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="new_id")
    private List<NewsImage> newsImages = new ArrayList<>();

    /*Constructors*/
    public News() {
    }

    public News(String title, String text) {
        this.title = title;
        this.content = text;
    }

    /*Getters/Setters*/
    public int getNewId() {
        return newId;
    }

    public void setNewId(int newId) {
        this.newId = newId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void addImage(NewsImage newsImage){
        newsImages.add(newsImage);
    }

    public List<NewsImage> getNewsImages() {
        return newsImages;
    }

    public void setNewsImages(List<NewsImage> newsImages) {
        this.newsImages = newsImages;
    }

    /*Methods*/
    @Override
    public String toString() {
        return "News{" +
                "newId=" + newId +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
