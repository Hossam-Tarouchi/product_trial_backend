package com.example.product_trial.wish_list.services.implementations;

import com.example.product_trial.product.models.Product;
import com.example.product_trial.product.repositories.ProductRepository;
import com.example.product_trial.product.utils.mappers.ProductMapper;
import com.example.product_trial.user.models.UserEntity;
import com.example.product_trial.wish_list.models.WishListEntity;
import com.example.product_trial.wish_list.repositories.WishListRepository;
import com.example.product_trial.wish_list.services.interfaces.WishListService;
import jakarta.transaction.Transactional;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WishListServiceImpl implements WishListService {

    private final WishListRepository wishListRepository;
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public WishListServiceImpl(WishListRepository wishListRepository, ProductRepository productRepository, ProductMapper productMapper) {
        this.wishListRepository = wishListRepository;
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    @Override
    public List<Product> getUserWishList() {
        List<Long> product_ids = wishListRepository.findAll().stream().map(WishListEntity::getProductId).toList();
        return productRepository.findAllById(product_ids).stream().map(productMapper::toProduct).toList();
    }

    @Override
    public Product addToWishList(Product product) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserEntity currentUser = (UserEntity) authentication.getPrincipal();
        wishListRepository.save(
                WishListEntity.builder()
                    .userId(currentUser.getId())
                    .productId(product.getId())
                .build()
        );
        return product;
    }

    @Override
    public Product removeFromWishList(Product product) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserEntity currentUser = (UserEntity) authentication.getPrincipal();
        wishListRepository.deleteByUserIdAndProductId(currentUser.getId(), product.getId());
        return product;
    }
}
