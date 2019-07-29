package com.smartshop.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="news_image")
@IdClass(NewsImageid.class)
public class NewsImage implements Serializable {
    /*Fields*/
    @Id
    @Column(name="new_id")
    private int newId;

    @Id
    @Column(name="name_image")
    private String nameImage;

    /*Constructors*/
    public NewsImage() {
    }

    public NewsImage(int newId, String nameImage) {
        this.newId = newId;
        this.nameImage = nameImage;
    }

    /*Getters/Setters*/
    public int getNewId() {
        return newId;
    }

    public void setNewId(int newId) {
        this.newId = newId;
    }

    public String getNameImage() {
        return nameImage;
    }

    public void setNameImage(String nameImage) {
        this.nameImage = nameImage;
    }

    @Override
    public String toString() {
        return "NewsImageRepository{" +
                "newId=" + newId +
                ", nameImage='" + nameImage + '\'' +
                '}';
    }
}
