package com.smilestone.smarket_api.product.db.repository.product;

import com.smilestone.smarket_api.product.db.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long>, ProductRepositoryCustom {
    List<Product> findAllBySellerId(Long sellerId);
    List<Product> findAllByBuyerId(Long sellerId);
}
