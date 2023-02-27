package com.smilestone.smarket_api.product.db.repository.purchaseInfo;

import com.smilestone.smarket_api.product.dto.ListProductDTO;
import com.smilestone.smarket_api.product.dto.ProductDTO;

import java.util.List;

public interface PurchaseInfoRepositoryCustom {
    List<ListProductDTO> getProductsByBuyerId(Long buyerId);
}
