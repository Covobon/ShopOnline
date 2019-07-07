package com.smartshop.dao.product;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="product")
public class Product {
    /*Define fields*/

    @Id
    @Column(name="product_id")
    private String productId;

    @Column(name="name")
    private String name;

    @Column(name="price")
    private int price;

    @Column(name="category")
    private String category;

    @Column(name="status")
    private String status;

    @Column(name="amount")
    private int amount;

    @OneToMany(fetch= FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="product_id")
    private List<ProductImage> productImages = new ArrayList<>();

    /*Define constructors*/
    public Product() {
    }

    public Product(String productId, String name, int price, String category, String status, int amount) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.category = category;
        this.status = status;
        this.amount = amount;
    }

    /*Define getters/setters*/

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void addProductImage(ProductImage productImage){
        productImages.add(productImage);
    }

    public List<ProductImage> getProductImages(){
        return productImages;
    }
    /*Methods*/

    @Override
    public String toString() {
        return "Product{" +
                "productId='" + productId + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", category='" + category + '\'' +
                ", status='" + status + '\'' +
                ", amount=" + amount +
                '}';
    }
}
