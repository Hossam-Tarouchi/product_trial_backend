package com.example.product_trial.shopping_cart.controllers;
import com.example.product_trial.product.models.Product;
import com.example.product_trial.shared.constants.ConstantMessages;
import com.example.product_trial.shared.models.CustomHttpResponse;
import com.example.product_trial.shopping_cart.services.interfaces.ShoppingCartService;
import com.example.product_trial.wish_list.services.interfaces.WishListService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/shoppingcart")
public class ShoppingCartController {

    private final ShoppingCartService shoppingCartService;
    public ShoppingCartController(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    @GetMapping
    public ResponseEntity<CustomHttpResponse> getShoppingCart() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(
                    CustomHttpResponse.builder()
                        .success(true)
                        .message(ConstantMessages.EMPTY_MESSAGE)
                        .data(shoppingCartService.getUserShoppingCart())
                    .build()
                );
    }

    @PostMapping
    public ResponseEntity<CustomHttpResponse> addToShoppingCart(@RequestBody Product product) {

        return ResponseEntity.status(HttpStatus.OK)
            .body(
                CustomHttpResponse.builder()
                    .success(true)
                    .message(ConstantMessages.PRODUCT_SUCCESSFULLY_ADDED_TO_SHOPPING_CART)
                    .data(shoppingCartService.addToShoppingCart(product))
                    .build()
            );
    }

    @DeleteMapping("/{product_id}")
    public ResponseEntity<CustomHttpResponse> deleteFromWishList(@PathVariable Long product_id) {
        shoppingCartService.removeFromShoppingCart(product_id);
        return ResponseEntity.status(HttpStatus.OK)
            .body(CustomHttpResponse.builder()
                    .success(true)
                    .message(ConstantMessages.PRODUCT_SUCCESSFULLY_REMOVED_FROM_SHOPPING_CART)
                    .build()
            );
    }

}
