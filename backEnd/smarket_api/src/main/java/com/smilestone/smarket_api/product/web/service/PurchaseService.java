package com.smilestone.smarket_api.product.web.service;

import com.smilestone.smarket_api.product.db.entity.Product;
import com.smilestone.smarket_api.product.db.entity.PurchaseInfo;
import com.smilestone.smarket_api.product.db.repository.purchaseInfo.PurchaseInfoRepository;
import com.smilestone.smarket_api.product.dto.ListProductDTO;
import com.smilestone.smarket_api.product.dto.ProductDTO;
import com.smilestone.smarket_api.product.dto.PurchaseInfoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PurchaseService {
    private final PurchaseInfoRepository purchaseInfoRepository;

    public void purchaseProduct(PurchaseInfoDTO purchaseInfo) {
        purchaseInfoRepository.save(new PurchaseInfo(
                null,
                purchaseInfo.getSellerId(),
                purchaseInfo.getBuyerId(),
                new Product(purchaseInfo.getProductId())
        ));
    }

    public List<ListProductDTO> purchasedProducts(Long userId) {
        return purchaseInfoRepository.getProductsByBuyerId(userId);
    }
}
