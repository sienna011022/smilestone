package com.smilestone.smarket_api.product.web.controller;

import com.smilestone.smarket_api.product.dto.ListProductDTO;
import com.smilestone.smarket_api.product.dto.ProductDTO;
import com.smilestone.smarket_api.product.dto.PurchaseInfoDTO;
import com.smilestone.smarket_api.product.dto.RequestProductDTO;
import com.smilestone.smarket_api.product.web.service.ProductService;
import com.smilestone.smarket_api.product.web.service.PurchaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final PurchaseService purchaseService;

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
        return purchaseService.purchasedProducts(buyerId);
    }

    @PostMapping("/product/post")
    public ProductDTO postProduct(@RequestBody RequestProductDTO product) {
        return productService.postProduct(product);
    }

    @PostMapping("/product/purchase")
    public void purchaseProduct(@RequestBody PurchaseInfoDTO info) {
        purchaseService.purchaseProduct(info);
    }

    @GetMapping("/product/buyer")
    public List<Long> productBuyers(@RequestParam Long productId) {
        return productService.getProductBuyers(productId);
    }

    @GetMapping("/product/delete")
    public void deleteProduct(@RequestParam Long productId) {
        productService.deleteProduct(productId);
    }

    @PostMapping("/product/update")
    public void updateProduct(@RequestBody ProductDTO productDTO) {
        productService.updateProduct(productDTO);
    }

    @GetMapping("/product/category")
    public List<ListProductDTO> updateProduct(@RequestParam String category) {
        return productService.getCategoryProducts(category);
    }
}
