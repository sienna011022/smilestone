package com.smilestone.smarket_api.product.db.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Table(schema = "smile-product")
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long stateId;

    public Long sellerId;
    public Long buyerId;

    @ManyToOne(targetEntity = Product.class, fetch = FetchType.LAZY)
    public Product product;
}
