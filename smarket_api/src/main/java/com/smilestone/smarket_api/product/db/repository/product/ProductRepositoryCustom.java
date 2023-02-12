package com.smilestone.smarket_api.product.db.repository.product;

import com.smilestone.smarket_api.product.dto.ProductDTO;

import java.util.List;

public interface ProductRepositoryCustom{
    List<ProductDTO> getProductsByTitle(String title);
}
