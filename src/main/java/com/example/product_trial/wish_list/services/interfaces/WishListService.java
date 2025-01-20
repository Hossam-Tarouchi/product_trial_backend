package com.example.product_trial.wish_list.services.interfaces;

import com.example.product_trial.product.models.Product;

import java.util.List;

public interface WishListService {

    List<Product> getUserWishList();
    Product addToWishList(Product product);
    Product removeFromWishList(Product product);

}
