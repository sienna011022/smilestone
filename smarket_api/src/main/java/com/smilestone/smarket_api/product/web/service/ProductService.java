package com.smilestone.smarket_api.product.web.service;

import com.smilestone.smarket_api.product.db.entity.Product;
import com.smilestone.smarket_api.product.db.repository.product.ProductRepository;
import com.smilestone.smarket_api.product.dto.ListProductDTO;
import com.smilestone.smarket_api.product.dto.ProductDTO;
import com.smilestone.smarket_api.product.dto.RequestProductDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    @Transactional(transactionManager = "secondTransactionManager")
    public ProductDTO getProductById(Long productId) {
        return new ProductDTO(productRepository.findById(productId).orElseGet(Product::new)) ;
    }

    @Transactional(transactionManager = "secondTransactionManager")
    public List<ListProductDTO> getProductsBySellerId(Long sellerId) {
        return productRepository.findAllBySellerId(sellerId).stream().map(ListProductDTO::new).collect(Collectors.toList());
    }

    @Transactional(transactionManager = "secondTransactionManager")
    public List<ListProductDTO> getProductsByIdBuyerId(Long buyerId) {
        return productRepository.findAllByBuyerId(buyerId).stream().map(ListProductDTO::new).collect(Collectors.toList());
    }


    @Transactional(transactionManager = "secondTransactionManager")
    public List<ProductDTO> getProductsById() {
        return productRepository.findAll().stream().map(ProductDTO::new).collect(Collectors.toList());
    }

    @Transactional(transactionManager = "secondTransactionManager")
    public List<ListProductDTO> getProductListById() {
        return productRepository.findAll().stream().map(ListProductDTO::new).collect(Collectors.toList());
    }

    @Transactional(transactionManager = "secondTransactionManager")
    public ProductDTO postProduct(RequestProductDTO product) {
        return new ProductDTO(productRepository.save(new Product(
                null,
                product.getSellerId(),
                null,
                product.getTitle(),
                product.getContent(),
                product.getPrice(),
                false,
                0L,
                LocalDateTime.now()
        )));
    }

    @Transactional(transactionManager = "secondTransactionManager")
    public List<ListProductDTO> getProductsByTitle(String title) {
        return productRepository.getProductsByTitle(title);
    }
}
