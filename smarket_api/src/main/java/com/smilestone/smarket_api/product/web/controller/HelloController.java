package com.smilestone.smarket_api.product.web.controller;

import com.smilestone.smarket_api.product.db.entity.Product;
import com.smilestone.smarket_api.product.db.repository.product.ProductRepository;
import com.smilestone.smarket_api.product.dto.ProductDTO;
import com.smilestone.smarket_api.product.web.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class HelloController {
    private final ProductService productService;

    @GetMapping("/product")
    public ProductDTO getProductById(@RequestParam Long productId) {
        return productService.getProductById(productId);
    }

    @GetMapping("/products")
    public List<ProductDTO> getProductsById(@RequestParam Long productId) {
        return productService.getProductsById(productId);
    }

    @PostMapping("/product")
    public ProductDTO getProductsById(@RequestBody ProductDTO product) {
        return productService.postProduct(product);
    }
}
