package com.smilestone.smarket_api.product.db.repository.purchaseInfo;

import com.smilestone.smarket_api.product.db.entity.PurchaseInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseInfoRepository extends JpaRepository<PurchaseInfo, Long>, PurchaseInfoRepositoryCustom {
}
