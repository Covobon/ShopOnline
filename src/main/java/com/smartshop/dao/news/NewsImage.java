package com.smartshop.dao.news;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="news_image")
public class NewsImage implements Serializable {
    /*Fields*/
    @Column(name="new_id")
    private int newId;

    @Id
    @Column(name="name_image")
    private String name_image;

    /*Constructors*/
    public NewsImage() {
    }

    public NewsImage(int newId, String name_image) {
        this.newId = newId;
        this.name_image = name_image;
    }

    /*Getters/Setters*/
    public int getNewId() {
        return newId;
    }

    public void setNewId(int newId) {
        this.newId = newId;
    }

    public String getName_image() {
        return name_image;
    }

    public void setName_image(String name_image) {
        this.name_image = name_image;
    }

    /*Methods*/

    @Override
    public String toString() {
        return "NewsImage{" +
                "newId=" + newId +
                ", name_image='" + name_image + '\'' +
                '}';
    }
}
