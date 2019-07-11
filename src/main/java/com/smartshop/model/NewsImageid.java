package com.smartshop.model;

import java.io.Serializable;
import java.util.Objects;

public class NewsImageid implements Serializable {

    private int newId;

    private String nameImage;

    //====================


    public NewsImageid() {
    }

    public NewsImageid(int newId, String nameImage) {
        this.newId = newId;
        this.nameImage = nameImage;
    }
    //=======================


    public int getNewId() {
        return newId;
    }

    public String getNameImage() {
        return nameImage;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        NewsImageid other = (NewsImageid) obj;
        if (nameImage == null) {
            if (other.nameImage != null)
                return false;
        } else if (!nameImage.equals(other.nameImage))
            return false;
        if (newId != other.newId)
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(newId, nameImage);
    }
    /*
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((nameImage == null) ? 0 : nameImage.hashCode());
        result = prime * result + newId;
        return result;
    }
    */
}
