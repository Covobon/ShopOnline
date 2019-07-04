package com.smartshop.dao.product;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class ProductImageDaoImp implements ProductImageDao {

    @Autowired
    private EntityManager entityManager;

    @Override
    public void add(ProductImage productImage) {
        entityManager.merge(productImage);
    }

    @Override
    public List<ProductImage> findAllByProduct(String productId) {
        TypedQuery<ProductImage> query = entityManager.createQuery("select pi from ProductImage pi where pi.productId = :productId"
                , ProductImage.class);
        query.setParameter("productId", productId);
        return query.getResultList();
    }

    @Override
    public void delete(ProductImage productImage) {
        entityManager.remove(productImage);
    }

    @Override
    public void deleteByProduct(Product product) {
        Query query = entityManager.createQuery("delete from ProductImage pi where pi.productId = :productId");
        query.setParameter("productId", product.getProductId());
        query.executeUpdate();

    }
}
