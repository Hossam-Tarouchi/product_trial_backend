package com.example.product_trial.product.services.interfaces;

import com.example.product_trial.product.models.Product;

import java.util.List;

public interface ProductService {

    List<Product> getAllProducts();
    Product createProduct(Product product);

    Product getProductById(Long id);
    Product updateProduct(Long id, Product product);
    void deleteProduct(Long id);

}
