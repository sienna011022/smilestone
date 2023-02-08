package com.smilestone.smarket_api.product.db.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Table(schema = "smile-product")
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    private Long sellerId;
    private Long buyerId;
    private String title;
    private String content;
    private Long price;
    private Boolean state;
    private Long view;
    private LocalDateTime localDateTime;
}
