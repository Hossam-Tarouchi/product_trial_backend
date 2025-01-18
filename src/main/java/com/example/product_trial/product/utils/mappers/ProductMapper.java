package com.example.product_trial.product.utils.mappers;

import com.example.product_trial.product.models.Product;
import com.example.product_trial.product.models.ProductEntity;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    public Product toProduct(ProductEntity productEntity) {
        return Product.builder()
                .id(productEntity.getId())
                .code(productEntity.getCode())
                .name(productEntity.getName())
                .description(productEntity.getDescription())
                .image(productEntity.getImage())
                .category(productEntity.getCategory())
                .price(productEntity.getPrice())
                .quantity(productEntity.getQuantity())
                .internalReference(productEntity.getInternalReference())
                .shellId(productEntity.getShellId())
                .inventoryStatus(productEntity.getInventoryStatus())
                .rating(productEntity.getRating())
                .createdAt(productEntity.getCreatedAt())
                .updatedAt(productEntity.getUpdatedAt())
                .build();
    }

    public ProductEntity toProductEntity(Product product) {
        return ProductEntity.builder()
                .id(product.getId())
                .code(product.getCode())
                .name(product.getName())
                .description(product.getDescription())
                .image(product.getImage())
                .category(product.getCategory())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .internalReference(product.getInternalReference())
                .shellId(product.getShellId())
                .inventoryStatus(product.getInventoryStatus())
                .rating(product.getRating())
                .createdAt(product.getCreatedAt())
                .updatedAt(product.getUpdatedAt())
                .build();
    }
}
