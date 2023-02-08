package com.smilestone.smarket_api.product.web.service;

import com.smilestone.smarket_api.product.db.entity.Product;
import com.smilestone.smarket_api.product.db.repository.product.ProductRepository;
import com.smilestone.smarket_api.product.dto.ProductDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    @Transactional
    public ProductDTO getProductById(Long productId) {
        return new ProductDTO(productRepository.findById(productId).orElseGet(Product::new));
    }


    @Transactional
    public List<ProductDTO> getProductsById(Long productId) {
        return productRepository.findAllById(List.of(productId)).stream().map(ProductDTO::new).toList();
    }

    @Transactional
    public ProductDTO postProduct(ProductDTO product) {
        return new ProductDTO(productRepository.save(new Product(
                product.getProductId(),
                product.getSellerId(),
                product.getBuyerId(),
                product.getTitle(),
                product.getContent(),
                product.getPrice(),
                product.getState(),
                product.getView(),
                product.getLocalDateTime()
        )));
    }
}
