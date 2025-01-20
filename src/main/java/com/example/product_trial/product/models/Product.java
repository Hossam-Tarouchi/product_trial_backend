package com.example.product_trial.product.models;

import jakarta.persistence.GeneratedValue;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Product {
    private Long id;
    private String code;
    private String name;
    private String description;
    private String image;
    private String category;
    private Double price;
    private Integer quantity;
    private String internalReference;
    private Integer shellId;
    private String inventoryStatus; //"INSTOCK" | "LOWSTOCK" | "OUTOFSTOCK"
    private Double rating;
    private Integer createdAt;
    private Integer updatedAt;
}
