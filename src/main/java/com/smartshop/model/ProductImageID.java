package com.smartshop.model;

import java.io.Serializable;
import java.util.Objects;

public class ProductImageID implements Serializable {

    private String productId;

    private String nameImage;

    public ProductImageID(String productId, String nameImage) {
        this.productId = productId;
        this.nameImage = nameImage;
    }

    public ProductImageID() {
    }

    public String getProductId() {
        return productId;
    }

    public String getNameImage() {
        return nameImage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductImageID)) return false;
        ProductImageID that = (ProductImageID) o;
        return Objects.equals(getProductId(), that.getProductId()) &&
                Objects.equals(getNameImage(), that.getNameImage());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getProductId(), getNameImage());
    }
}

