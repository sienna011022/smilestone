package com.smilestone.smarket_api.product.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestProductDTO {
    private Long sellerId;
    private String title;
    private String content;
    private Long price;
    private String category;
}
