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
    private Long buyerId;
    private String title;
    private String content;
    private Long price;
    private Boolean state;
    private Long view;
    private LocalDateTime localDateTime;

    public ProductDTO(Product product) {
        this.productId = product.getProductId();
        this.sellerId = product.getSellerId();
        this.buyerId = product.getBuyerId();
        this.title = product.getTitle();
        this.content = product.getContent();
        this.price = product.getPrice();
        this.state = product.getState();
        this.view = product.getView();
        this.localDateTime = product.getLocalDateTime();
    }
}
