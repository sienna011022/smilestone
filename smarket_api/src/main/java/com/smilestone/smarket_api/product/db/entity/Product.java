package com.smilestone.smarket_api.product.db.entity;

import com.smilestone.smarket_api.product.dto.ProductDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
    private String title;
    private String content;
    private Long price;
    private Boolean state;
    private Long view;
    private String category;
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    List<PurchaseInfo> purchaseInfos = new ArrayList<>();

    public Product(Long productId) {
        this.productId = productId;
    }

    public Product update(ProductDTO product) {
        this.title = product.getTitle();
        this.content = product.getContent();
        this.price = product.getPrice();
        this.state = product.getState();
        this.view = product.getView();
        this.category = product.getCategory();
        return this;
    }
}
