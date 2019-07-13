//package com.smartshop.model;
//
//import java.io.Serializable;
//import java.util.Objects;
//
//public class ProductDetailID implements Serializable {
//
//    private String productId;
//
//    private String nameImage;
//
//    //=================================
//    public ProductDetailID() {
//    }
//
//    public ProductDetailID(String productId, String nameImage) {
//        this.productId = productId;
//        this.nameImage = nameImage;
//    }
//    //=================================
//
//    public String getProductId() {
//        return productId;
//    }
//
//    public String getNameImage() {
//        return nameImage;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (!(o instanceof ProductDetailID)) return false;
//        ProductDetailID that = (ProductDetailID) o;
//        return getProductId().equals(that.getProductId()) &&
//                getNameImage().equals(that.getNameImage());
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(getProductId(), getNameImage());
//    }
//}
