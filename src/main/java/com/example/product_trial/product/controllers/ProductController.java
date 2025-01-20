package com.example.product_trial.product.controllers;

import com.example.product_trial.shared.models.CustomHttpResponse;
import com.example.product_trial.product.models.Product;
import com.example.product_trial.product.services.interfaces.ProductService;
import com.example.product_trial.shared.constants.ConstantMessages;
import com.example.product_trial.product.utils.exceptions.ProductDoesntExistException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {

    private final ProductService productService;
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<CustomHttpResponse> getAllProducts() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(CustomHttpResponse.builder()
                        .success(true)
                        .message(ConstantMessages.EMPTY_MESSAGE)
                        .data(productService.getAllProducts())
                        .build());
    }

    @PostMapping
    public ResponseEntity<CustomHttpResponse> createProduct(@RequestBody Product product) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(CustomHttpResponse.builder()
                        .success(true)
                        .message(ConstantMessages.PRODUCT_SUCCESSFULLY_CREATED)
                        .data(productService.createProduct(product))
                        .build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomHttpResponse> getProductById(@PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(CustomHttpResponse.builder()
                            .success(true)
                            .message(ConstantMessages.EMPTY_MESSAGE)
                            .data(productService.getProductById(id))
                            .build());
        } catch (ProductDoesntExistException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(CustomHttpResponse.builder()
                            .success(false)
                            .message(e.getMessage())
                            .build());
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CustomHttpResponse> updateProduct(@PathVariable Long id, @RequestBody Product product) {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(CustomHttpResponse.builder()
                            .success(true)
                            .message(ConstantMessages.PRODUCT_SUCCESSFULLY_UPDATE)
                            .data(productService.updateProduct(id, product))
                            .build());
        } catch (ProductDoesntExistException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(CustomHttpResponse.builder()
                            .success(false)
                            .message(e.getMessage())
                            .build());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CustomHttpResponse> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(CustomHttpResponse.builder()
                        .success(true)
                        .message(ConstantMessages.PRODUCT_SUCCESSFULLY_DELETED)
                        .build());
    }
}
