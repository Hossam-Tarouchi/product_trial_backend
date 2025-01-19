package com.example.product_trial.wish_list.controllers;
import com.example.product_trial.product.models.Product;
import com.example.product_trial.shared.constants.ConstantMessages;
import com.example.product_trial.shared.models.CustomHttpResponse;
import com.example.product_trial.wish_list.services.interfaces.WishListService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/wishlist")
public class WishListController {

    private final WishListService wishListService;
    public WishListController(WishListService wishListService) {
        this.wishListService = wishListService;
    }

    @GetMapping
    public ResponseEntity<CustomHttpResponse> getWishList() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(
                    CustomHttpResponse.builder()
                        .success(true)
                        .message(ConstantMessages.EMPTY_MESSAGE)
                        .data(wishListService.getUserWishList())
                    .build()
                );
    }

    @PostMapping
    public ResponseEntity<CustomHttpResponse> addToWishList(@RequestBody Product product) {

        return ResponseEntity.status(HttpStatus.OK)
            .body(
                CustomHttpResponse.builder()
                    .success(true)
                    .message(ConstantMessages.PRODUCT_SUCCESSFULLY_ADDED_TO_WISH_LIST)
                    .data(wishListService.addToWishList(product))
                    .build()
            );
    }

    @DeleteMapping
    public ResponseEntity<CustomHttpResponse> deleteFromWishList(@RequestBody Product product) {
        wishListService.removeFromWishList(product);
        return ResponseEntity.status(HttpStatus.OK)
            .body(CustomHttpResponse.builder()
                    .success(true)
                    .message(ConstantMessages.PRODUCT_SUCCESSFULLY_REMOVED_FROM_WISH_LIST)
                    .build()
            );
    }

}
