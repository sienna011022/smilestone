package com.smilestone.smarket_api.product.dto;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseInfoDTO {
    private Long sellerId;
    private Long buyerId;
    private Long productId;
}
