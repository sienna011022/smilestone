package com.smilestone.smarket_api.product.db.repository.product;

import com.smilestone.smarket_api.product.db.entity.Product;
import com.smilestone.smarket_api.product.db.entity.PurchaseInfo;
import com.smilestone.smarket_api.product.dto.ListProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;
import java.util.stream.Collectors;

public class ProductRepositoryImpl implements ProductRepositoryCustom {
    @Autowired
    @Qualifier("secondEntityManager")
    private EntityManagerFactory emf;

    @Override
    public List<ListProductDTO> getProductsByTitle(String title) {
        EntityManager em = emf.createEntityManager();
        return em.createQuery("select p from Product p where p.title like ?1", Product.class)
                .setParameter(1, "%" + title + "%")
                .getResultStream().map(ListProductDTO::new).collect(Collectors.toList());
    }

    @Override
    public List<Long> getProductBuyers(Long productId) {
        EntityManager em = emf.createEntityManager();
        return em.createQuery("select p from Product p join fetch p.purchaseInfos where p.productId=?1", Product.class)
                .setParameter(1, productId)
                .getSingleResult().getPurchaseInfos().stream().map(PurchaseInfo::getBuyerId).collect(Collectors.toList());
    }
}
