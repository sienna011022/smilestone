package com.smilestone.smarket_api.product.web.controller;

import com.smilestone.smarket_api.product.db.entity.Product;
import com.smilestone.smarket_api.product.db.repository.product.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class HelloController {
    private final ProductRepository productRepository;
    @GetMapping("/")
    public void hello() {
        productRepository.save(new Product());
    }
}
