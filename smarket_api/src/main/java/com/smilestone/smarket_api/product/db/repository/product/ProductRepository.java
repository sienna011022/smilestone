package com.smilestone.smarket_api.product.db.repository.product;

import com.smilestone.smarket_api.product.db.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long>, ProductRepositoryCustom {
}
