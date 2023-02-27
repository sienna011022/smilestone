package com.smilestone.smarket_api.product.db.repository.product;

import com.smilestone.smarket_api.product.dto.ListProductDTO;

import java.util.List;

public interface ProductRepositoryCustom{
    List<ListProductDTO> getProductsByTitle(String title);

    List<Long> getProductBuyers(Long productId);
}
