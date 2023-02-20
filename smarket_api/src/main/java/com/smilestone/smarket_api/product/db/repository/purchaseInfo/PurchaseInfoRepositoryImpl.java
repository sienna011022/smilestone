package com.smilestone.smarket_api.product.db.repository.purchaseInfo;

import com.smilestone.smarket_api.product.db.entity.PurchaseInfo;
import com.smilestone.smarket_api.product.dto.ListProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;
import java.util.stream.Collectors;

public class PurchaseInfoRepositoryImpl implements PurchaseInfoRepositoryCustom {
    @Autowired
    @Qualifier("secondEntityManager")
    private EntityManagerFactory emf;

    @Override
    public List<ListProductDTO> getProductsByBuyerId(Long buyerId) {
        EntityManager em = emf.createEntityManager();
        return em.createQuery("select pi from PurchaseInfo pi join fetch pi.product where pi.buyerId=?1", PurchaseInfo.class)
                .setParameter(1, buyerId)
                .getResultList()
                .stream().map(v -> new ListProductDTO(v.getProduct())).collect(Collectors.toList());
    }
}
