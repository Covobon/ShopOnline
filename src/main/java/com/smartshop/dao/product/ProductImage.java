package com.smartshop.dao.product;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name="ProductImage")
@Table(name="product_image")
public class ProductImage implements Serializable {

    /*Fields*/
    @Column(name = "product_id")
    private String productId;

    @Id
    @Column(name="name_image")
    private String nameImage;

    /*Constructor*/
    public ProductImage() {
    }

    public ProductImage(String productId, String path) {
        this.productId = productId;
        this.nameImage = path;
    }

    /*Getters/Setters*/

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getNameImage() {
        return nameImage;
    }

    public void setNameImage(String path) {
        this.nameImage = path;
    }

    /*Methods*/
    @Override
    public String toString() {
        return "ProductImage{" +
                "productId='" + productId + '\'' +
                ", nameImage='" + nameImage + '\'' +
                '}';
    }
}
