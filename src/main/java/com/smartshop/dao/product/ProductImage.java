package com.smartshop.dao.product;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="product_image")
public class ProductImage {

    /*Fields*/
    @Column(name="productId")
    private String productId;

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
