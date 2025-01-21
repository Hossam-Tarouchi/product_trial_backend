package com.example.product_trial.shopping_cart.services.implementations;

import com.example.product_trial.product.models.Product;
import com.example.product_trial.product.repositories.ProductRepository;
import com.example.product_trial.product.utils.mappers.ProductMapper;
import com.example.product_trial.shopping_cart.models.ShoppingCartEntity;
import com.example.product_trial.shopping_cart.repositories.ShoppingCartRepository;
import com.example.product_trial.shopping_cart.services.interfaces.ShoppingCartService;
import com.example.product_trial.user.models.UserEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private final ShoppingCartRepository shoppingCartRepository;
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ShoppingCartServiceImpl(ShoppingCartRepository shoppingCartRepository, ProductRepository productRepository, ProductMapper productMapper) {
        this.shoppingCartRepository = shoppingCartRepository;
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    @Override
    public List<Product> getUserShoppingCart() {
        List<Long> product_ids = shoppingCartRepository.findAll().stream().map(ShoppingCartEntity::getProductId).toList();
        return productRepository.findAllById(product_ids).stream().map(productMapper::toProduct).toList();
    }

    @Override
    public Product addToShoppingCart(Product product) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserEntity currentUser = (UserEntity) authentication.getPrincipal();
        shoppingCartRepository.save(
                ShoppingCartEntity.builder()
                    .userId(currentUser.getId())
                    .productId(product.getId())
                .build()
        );
        return product;
    }

    @Override
    public void removeFromShoppingCart(Long product_id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserEntity currentUser = (UserEntity) authentication.getPrincipal();
        shoppingCartRepository.deleteByUserIdAndProductId(currentUser.getId(), product_id);
    }
}
