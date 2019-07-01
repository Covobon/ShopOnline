package com.smartshop.dao.news;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="news_image")
public class NewsImage {
    /*Fields*/
    @Column(name="new_id")
    private int newId;

    @Column(name="path")
    private String path;

    /*Constructors*/
    public NewsImage() {
    }

    public NewsImage(int newId, String path) {
        this.newId = newId;
        this.path = path;
    }

    /*Getters/Setters*/
    public int getNewId() {
        return newId;
    }

    public void setNewId(int newId) {
        this.newId = newId;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    /*Methods*/
    @Override
    public String toString() {
        return "NewsImage{" +
                "newId=" + newId +
                ", path='" + path + '\'' +
                '}';
    }
}
