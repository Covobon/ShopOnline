package com.smartshop.repo.product;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name="product_image")
public class ProductImage implements Serializable {

    /*Fields*/
    @Id
    @Column(name="product_id")
    private String productId;

    @Id
    @Column(name="path")
    private String path;

    /*Constructor*/
    public ProductImage() {
    }

    public ProductImage(String productId, String path) {
        this.productId = productId;
        this.path = path;
    }

    /*Getters/Setters*/

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
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
        return "ProductImage{" +
                "productId='" + productId + '\'' +
                ", path='" + path + '\'' +
                '}';
    }
}
