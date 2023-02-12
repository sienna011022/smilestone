package com.smilestone.smarket_api.product.web.controller;

import com.smilestone.smarket_api.product.dto.ListProductDTO;
import com.smilestone.smarket_api.product.dto.ProductDTO;
import com.smilestone.smarket_api.product.dto.RequestProductDTO;
import com.smilestone.smarket_api.product.web.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("/product/id")
    public ProductDTO getProductById(@RequestParam Long productId) {
        return productService.getProductById(productId);
    }

    @GetMapping("/product/all")
    public List<ProductDTO> getProductsById() {
        return productService.getProductsById();
    }

    @GetMapping("/product/list/all")
    public List<ListProductDTO> getProductListById() {
        return productService.getProductListById();
    }

    @GetMapping("/product/title")
    public List<ListProductDTO> getProductsByTitle(@RequestParam String title) {
        return productService.getProductsByTitle(title);
    }

    @GetMapping("/product/seller/all")
    public List<ListProductDTO> getProductsBySellerId(@RequestParam Long sellerId) {
        return productService.getProductsBySellerId(sellerId);
    }

    @GetMapping("/product/buyer/all")
    public List<ListProductDTO> getProductsByBuyerId(@RequestParam Long buyerId) {
        return productService.getProductsByIdBuyerId(buyerId);
    }

    @PostMapping("/product/post")
    public ProductDTO postProduct(@RequestBody RequestProductDTO product) {
        return productService.postProduct(product);
    }
}
