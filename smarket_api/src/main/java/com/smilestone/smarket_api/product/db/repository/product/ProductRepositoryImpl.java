package com.smilestone.smarket_api.product.db.repository.product;

import com.smilestone.smarket_api.product.db.entity.Product;
import com.smilestone.smarket_api.product.dto.ProductDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.stream.Collectors;

public class ProductRepositoryImpl implements ProductRepositoryCustom {
    @Autowired
    @Qualifier("secondEntityManager")
    private EntityManagerFactory emf;

    @Override
    public List<ProductDTO> getProductsByTitle(String title) {
        EntityManager em = emf.createEntityManager();
        return em.createQuery("select p from Product p where p.title like ?1", Product.class)
                .setParameter(1, "%" + title + "%")
                .getResultList().stream().map(ProductDTO::new).collect(Collectors.toList());
    }
}
