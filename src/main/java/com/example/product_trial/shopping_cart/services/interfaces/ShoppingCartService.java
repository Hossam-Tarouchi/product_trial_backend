package com.example.product_trial.shopping_cart.services.interfaces;

import com.example.product_trial.product.models.Product;

import java.util.List;

public interface ShoppingCartService {

    List<Product> getUserShoppingCart();
    Product addToShoppingCart(Product product);
    Product removeFromShoppingCart(Product product);

}
