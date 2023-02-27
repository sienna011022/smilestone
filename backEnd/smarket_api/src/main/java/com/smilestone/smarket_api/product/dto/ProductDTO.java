package com.smilestone.smarket_api.product.dto;

import com.smilestone.smarket_api.product.db.entity.Product;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    private Long productId;
    private Long sellerId;
    private String title;
    private String content;
    private Long price;
    private String category;
    private Boolean state;
    private Long view;
    private LocalDateTime createdAt;

    public ProductDTO(Product product) {
        this.productId = product.getProductId();
        this.sellerId = product.getSellerId();
        this.title = product.getTitle();
        this.content = product.getContent();
        this.price = product.getPrice();
        this.state = product.getState();
        this.view = product.getView();
        this.category = product.getCategory();
        this.createdAt = product.getCreatedAt();
    }
}
