package com.smartshop.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="image")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="image_id")
    private int imageId;

    @Column(name="image_name")
    private String imageName;

    @ManyToMany(fetch = FetchType.EAGER,
            cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.REFRESH})
    @JoinTable(
            name="product_image",
            joinColumns = @JoinColumn(name="image_id"),
            inverseJoinColumns = @JoinColumn(name="product_id")
    )
    private List<Product> products;

    public Image() {
    }

    public Image(String imageName) {
        this.imageName = imageName;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }
}
