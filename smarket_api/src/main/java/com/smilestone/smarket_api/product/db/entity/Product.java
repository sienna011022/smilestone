package com.smilestone.smarket_api.product.db.entity;

import javax.persistence.*;

@Table(schema = "smile-product")
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;
}
