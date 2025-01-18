package com.example.product_trial.product.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "product")
@Builder
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
